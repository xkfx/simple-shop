package org.sample.shop.dto;

public class JsonError {

    private String message;

    public JsonError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
