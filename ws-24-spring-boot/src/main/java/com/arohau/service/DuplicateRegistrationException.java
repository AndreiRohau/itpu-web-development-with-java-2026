package com.arohau.service;

public class DuplicateRegistrationException extends RuntimeException {
    private final String field;

    public DuplicateRegistrationException(String field, String message) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
