package com.jobsity.challenge.bowling.exception;

public class InvalidSourceReaderException extends GeneralAppException {
    public InvalidSourceReaderException(String message) {
        super(message);
    }

    public InvalidSourceReaderException(String message, Throwable cause) {
        super(message, cause);
    }
}