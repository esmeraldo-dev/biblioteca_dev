package br.com.vinicius.biblioteca_dev.dto;

public record EmprestimoRequestDTO(
        Long usuarioId,
        Long livroId
) {
}
