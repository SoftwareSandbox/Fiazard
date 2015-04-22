package be.swsb.fiazard.common.error;

import java.util.List;

import com.google.common.collect.Lists;

public class ErrorRBuilderForTests {

    private String errorCode;
    private String message;
    private List<String> fields;
    private int status;

    public ErrorRBuilderForTests(){}

    public ErrorR build(){
        return new ErrorR(status, errorCode, message, fields);
    }

    public ErrorRBuilderForTests withStatus(int status) {
        this.status = status;
        return this;
    }

    public ErrorRBuilderForTests withErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public ErrorRBuilderForTests withMessage(String message) {
        this.message = message;
        return this;
    }

    public ErrorRBuilderForTests withFields(String... fields) {
        this.fields = Lists.newArrayList(fields);
        return this;
    }
}