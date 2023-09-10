package com.keer.common.exception;

/**
 * @author Bob
 * @date 2022-06-08
 */
public class CryptoException extends Exception{

    public CryptoException(String message) {
        super(message);
    }

    public CryptoException(Throwable cause) {
        super(cause);
    }

    public CryptoException(String message, Throwable cause) {
        super(message, cause);
    }
}
