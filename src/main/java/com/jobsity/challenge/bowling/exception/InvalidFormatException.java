package com.jobsity.challenge.bowling.exception;

public class InvalidFormatException extends GeneralAppException {
    public InvalidFormatException(String message) {
        super(message);
    }

    public InvalidFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}