package com.example.exception;

public class EnumNotFoundException extends Exception {

    private EnumNotFoundException(String message) {
        super(message);
    }

    public static EnumNotFoundException code() {
        return new EnumNotFoundException("not found code");
    }
}
