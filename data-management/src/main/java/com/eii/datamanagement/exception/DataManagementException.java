package com.eii.datamanagement.exception;

public class DataManagementException extends RuntimeException {
    private final int errorCode;
    private final String userMessage;

//    public DataManagementException(String message) {
//        super(message);
//        this.errorCode = errorCode;
//        this.userMessage = userMessage;
//    }

    public DataManagementException(String message, Throwable cause, int errorCode, String userMessage) {
        super(message, cause);
        this.errorCode = errorCode;
        this.userMessage = userMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getUserMessage() {
        return userMessage;
    }
}

