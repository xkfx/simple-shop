package org.sample.shop.common.exception;

public class ValidateException extends RuntimeException {
    public ValidateException(Throwable cause) {
        super(cause);
    }

    public ValidateException(String message) {
        super(message);
    }

    public ValidateException(String message, Throwable cause) {
        super(message, cause);
    }
}
