package br.com.vinicius.biblioteca_dev.services;

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

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public List<Livro> buscarLivroPorTitulo(String titulo) {
        List<Livro> livros = livroRepository.findByTituloContainingIgnoreCase(titulo);
        if (livros.isEmpty()) {
            throw new RuntimeException("Nenhum livro encontrado com o termo: " + titulo);
        }
        return livros;
    }

    public Livro buscarLivroPorId(Long id) {
        return livroRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Livro não encontrado com id: " + id)
        );
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
}
