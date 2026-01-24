package br.com.vinicius.biblioteca_dev.dto;

public record LivroResponseDTO(Long id,
                               String titulo,
                               String autor,
                               String status
) {
}
