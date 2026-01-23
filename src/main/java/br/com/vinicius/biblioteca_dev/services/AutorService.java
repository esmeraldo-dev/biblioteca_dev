package br.com.vinicius.biblioteca_dev.services;

import br.com.vinicius.biblioteca_dev.entities.Autor;
import br.com.vinicius.biblioteca_dev.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> listarTodos() {
        return autorRepository.findAll();
    }

    public Autor salvar(Autor autor) {
        return autorRepository.save(autor);
    }

    public Autor buscarPorId(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado!"));
    }

    public Autor atualizarAutorPorId(Long id, Autor autor) {
        Autor autorExistente = buscarPorId(id);
        Autor autorAtualizado = Autor.builder()
                .nome(autor.getNome() != null ? autor.getNome() : autorExistente.getNome())
                .nacionalidade(autor.getNacionalidade() != null ? autor.getNacionalidade() : autorExistente.getNacionalidade())
                .id(autorExistente.getId())
                .build();
        return autorRepository.saveAndFlush(autorAtualizado);
    }

    public void deletarAutorPorId(Long id) {
        if (!autorRepository.existsById(id)) {
            throw new RuntimeException("Autor não encontrado com id: " + id);
        }
        autorRepository.deleteById(id);
    }
}
