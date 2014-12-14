package be.craftsmen.fiazard.representation.error;


import be.craftsmen.fiazard.common.error.AppErrorCode;
import be.craftsmen.fiazard.exceptions.FiazardException;
import be.craftsmen.fiazard.representation.Representation;

public class ErrorR implements Representation {
    private String message;
    private String errorCode;
    private String[] fields;

    ErrorR(String errorCode, String message, String... fields){
        this.errorCode = errorCode;
        this.message = message;
        this.fields = fields;
    }

    public static ErrorR from(FiazardException exception){
        AppErrorCode errorCode = exception.getErrorCode();
        return new ErrorR(errorCode.getErrorCode(), exception.getMessage());
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String[] getFields() {
        return fields;
    }
}
