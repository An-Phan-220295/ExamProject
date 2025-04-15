package org.example.test.testazure.exceptionhandler;

import lombok.Getter;

@Getter
public enum ErrorCode {
    NO_DATA("E15", "There is no data"),
    ERROR_DATA("E16","The data field is wrong");

    private final String errorCode;
    private final String message;

    ErrorCode(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
