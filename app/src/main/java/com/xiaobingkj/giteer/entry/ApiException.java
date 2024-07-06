package com.xiaobingkj.giteer.entry;

public class ApiException extends Exception {
    private ErrorResponse errorResponse;
    private int statusCode;

    public ApiException(ErrorResponse errorResponse, int statusCode) {
        super(errorResponse.getMessage());
        this.errorResponse = errorResponse;
        this.statusCode = statusCode;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
