package be.craftsmen.fiazard.exceptions;


import be.craftsmen.fiazard.common.error.AppErrorCode;

public abstract class FiazardException extends RuntimeException {

    private static final long serialVersionUID = 3250434504031845434L;

    public FiazardException(String message) {
        super(message);
    }

    public abstract AppErrorCode getErrorCode();

    public abstract int getStatus();

    public abstract Iterable<String> getFields();
}
