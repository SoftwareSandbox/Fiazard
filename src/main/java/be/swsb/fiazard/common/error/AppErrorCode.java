package be.swsb.fiazard.common.error;

public enum AppErrorCode {
    ILLEGAL_ID("1", 200),
    UNAUTHORIZED("2", 403),;

    private String errorCode;
    private int statusCode;

    private AppErrorCode(String errorCode, int statusCode) {
        this.errorCode = errorCode;
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
