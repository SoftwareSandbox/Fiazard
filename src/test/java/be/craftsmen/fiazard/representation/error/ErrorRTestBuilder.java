package be.craftsmen.fiazard.representation.error;

import junit.framework.TestCase;

public class ErrorRTestBuilder {

    private String errorCode;
    private String message;
    private String[] fields;

    public ErrorRTestBuilder(){}

    public ErrorR build(){
        return new ErrorR(errorCode, message, fields);
    }

    public ErrorRTestBuilder withErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public ErrorRTestBuilder withMessage(String message) {
        this.message = message;
        return this;
    }

    public ErrorRTestBuilder withFields(String... fields) {
        this.fields = fields;
        return this;
    }
}