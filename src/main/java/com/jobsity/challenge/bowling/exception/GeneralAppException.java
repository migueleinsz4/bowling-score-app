package com.jobsity.challenge.bowling.exception;

public class GeneralAppException extends RuntimeException {
    public GeneralAppException(String message) {
        super(message);
    }

    public GeneralAppException(String message, Throwable cause) {
        super(message, cause);
    }
}