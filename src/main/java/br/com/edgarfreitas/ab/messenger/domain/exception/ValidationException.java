package br.com.edgarfreitas.ab.messenger.domain.exception;

public class ValidationException extends Exception
{
    public ValidationException(String message) {
        super(message);
    }
}
