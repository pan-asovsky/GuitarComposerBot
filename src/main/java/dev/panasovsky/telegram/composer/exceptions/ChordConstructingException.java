package dev.panasovsky.telegram.composer.exceptions;


public class ChordConstructingException extends IllegalArgumentException {

    private final String exception;

    public ChordConstructingException(final String exception) {
        this.exception = exception;
    }

    public String getException() {
        return exception;
    }

}