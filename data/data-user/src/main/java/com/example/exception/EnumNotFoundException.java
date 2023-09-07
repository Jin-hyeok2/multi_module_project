package com.example.exception;

public class EnumNotFoundException extends RuntimeException {

    private EnumNotFoundException(String message) {
        super(message);
    }

    public static EnumNotFoundException code() {
        return new EnumNotFoundException("not found code");
    }
}
