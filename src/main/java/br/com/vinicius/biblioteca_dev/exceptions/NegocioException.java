package br.com.vinicius.biblioteca_dev.exceptions;

public class NegocioException extends RuntimeException {
    public NegocioException(String message) {
        super(message);
    }
}
