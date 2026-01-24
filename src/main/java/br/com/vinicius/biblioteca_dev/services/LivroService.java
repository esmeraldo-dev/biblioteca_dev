package br.com.vinicius.biblioteca_dev.services;

import br.com.vinicius.biblioteca_dev.dto.LivroResponseDTO;
import br.com.vinicius.biblioteca_dev.entities.Livro;
import br.com.vinicius.biblioteca_dev.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Livro salvar(Livro livro) {
        livro.setStatus("DISPONÍVEL");
        return livroRepository.saveAndFlush(livro);
    }

    public List<LivroResponseDTO> listarTodos() {
        return livroRepository.findAll().stream()
                .map(l -> new LivroResponseDTO(
                        l.getId(),
                        l.getTitulo(),
                        l.getAutor().getNome(),
                        l.getStatus()))
                .toList();
    }

    public LivroResponseDTO buscarLivroPorId(Long id) {
        Livro livro = livroRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Livro não encontrado com id: " + id));

        return new  LivroResponseDTO(livro.getId(),
                livro.getTitulo(),
                livro.getAutor().getNome(),
                livro.getStatus());
    }

    public List<Livro> listarDisponiveis() {
        return livroRepository.findByStatusIgnoreCase("DISPONIVEL");
    }

    public List<Livro> buscarLivroPorTitulo(String titulo) {
        List<Livro> livros = livroRepository.findByTituloContainingIgnoreCase(titulo);
        if (livros.isEmpty()) {
            throw new RuntimeException("Nenhum livro encontrado com o termo: " + titulo);
        }
        return livros;
    }

    public Livro atualizarLivroPorId(Long id, Livro livroExistente) {
        Livro livroEntity = livroRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Livro não encontrado com id: " + id)
        );
        Livro livroAtualizado = Livro.builder()
                .titulo(livroExistente.getTitulo() != null ? livroExistente.getTitulo() : livroEntity.getTitulo())
                .autor(livroExistente.getAutor() != null ? livroExistente.getAutor() : livroEntity.getAutor())
                .anoPublicacao(livroExistente.getAnoPublicacao() != null ? livroExistente.getAnoPublicacao() : livroEntity.getAnoPublicacao())
                .status(livroExistente.getStatus() != null ? livroExistente.getStatus() : livroEntity.getStatus())
                .id(livroEntity.getId())
                .isbn(livroEntity.getIsbn())
                .build();
        return livroRepository.saveAndFlush(livroAtualizado);
    }

    public void deletarLivroPorId(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new RuntimeException("Livro não encontrado com id: " + id);
        }
        livroRepository.deleteById(id);
    }

}
