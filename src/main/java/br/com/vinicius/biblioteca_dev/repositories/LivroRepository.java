package br.com.vinicius.biblioteca_dev.repositories;

import br.com.vinicius.biblioteca_dev.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByTituloContainingIgnoreCase(String titulo);

    List<Livro> findByStatusIgnoreCase(String status);
}
