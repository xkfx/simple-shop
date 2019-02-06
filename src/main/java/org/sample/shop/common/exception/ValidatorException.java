package org.sample.shop.common.exception;

public class ValidatorException extends RuntimeException {
    public ValidatorException(Throwable cause) {
        super(cause);
    }

    public ValidatorException(String message) {
        super(message);
    }

    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public static ValidatorException undefined(String validatorName) {
        return new ValidatorException("validator not found, validatorName=" + validatorName);
    }
}
