package be.craftsmen.fiazard.common.error;

import com.google.common.collect.Lists;

import java.util.List;

public class ErrorRTestBuilder {

    private String errorCode;
    private String message;
    private List<String> fields;
    private int status;

    public ErrorRTestBuilder(){}

    public ErrorR build(){
        return new ErrorR(status, errorCode, message, fields);
    }

    public ErrorRTestBuilder withStatus(int status) {
        this.status = status;
        return this;
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
        this.fields = Lists.newArrayList(fields);
        return this;
    }
}