package org.sample.shop.common.exception;

public class DaoException extends RuntimeException {

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String s) {
        super(s);
    }
}
