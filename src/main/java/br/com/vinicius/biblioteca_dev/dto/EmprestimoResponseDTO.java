package br.com.vinicius.biblioteca_dev.dto;

import java.time.LocalDate;

public record EmprestimoResponseDTO(
        Long id,
        String usuarioNome,
        String livroTitulo,
        LocalDate dataEmprestimo,
        LocalDate dataDevolucaoPrevista,
        Double valorMulta
) {
}
