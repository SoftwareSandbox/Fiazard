package be.craftsmen.fiazard.exceptions;


import be.craftsmen.fiazard.common.error.AppErrorCode;

public abstract class FiazardException extends RuntimeException {

    public FiazardException(String message) {
        super(message);
    }

    public abstract AppErrorCode getErrorCode();
}
