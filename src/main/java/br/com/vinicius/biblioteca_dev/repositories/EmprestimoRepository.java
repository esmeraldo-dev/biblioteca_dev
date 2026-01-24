package br.com.vinicius.biblioteca_dev.repositories;

import br.com.vinicius.biblioteca_dev.entities.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    boolean existsByUsuarioIdAndDataDevolucaoPrevistaBeforeAndDataDevolucaoRealIsNull(Long usuarioId, LocalDate data);

    boolean existsByLivroIdAndDataDevolucaoRealIsNull(Long livroId);
}
