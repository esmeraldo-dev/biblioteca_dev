package br.com.vinicius.biblioteca_dev.repositories;

import br.com.vinicius.biblioteca_dev.entities.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {

}
