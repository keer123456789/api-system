package com.keer.common.exception;

/**
 *
 */
public class RequestBuilderException extends Exception {
    public RequestBuilderException(String message) {
        super(message);
    }

    public RequestBuilderException(Throwable cause) {
        super(cause);
    }

    public RequestBuilderException(String message, Throwable cause) {
        super(message, cause);
    }
}
