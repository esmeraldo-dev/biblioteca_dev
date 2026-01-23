package br.com.vinicius.biblioteca_dev.repositories;

import br.com.vinicius.biblioteca_dev.entities.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
}
