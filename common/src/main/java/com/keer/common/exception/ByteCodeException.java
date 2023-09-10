package com.keer.common.exception;

public class ByteCodeException extends Exception{

    public ByteCodeException(String message) {
        super(message);
    }

    public ByteCodeException(Throwable cause) {
        super(cause);
    }

    public ByteCodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
