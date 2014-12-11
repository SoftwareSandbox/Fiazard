package be.craftsmen.fiazardtje.exceptions;

public class IllegalIdException extends RuntimeException {
    public IllegalIdException(String id) {
        super(String.format("id invalid: %s", id));
    }
}
