package br.com.vinicius.biblioteca_dev.dto;

import java.time.LocalDateTime;

public record ErroRespostaDTO(
        String mensagem,
        int status,
        LocalDateTime timestamp
) {
}
