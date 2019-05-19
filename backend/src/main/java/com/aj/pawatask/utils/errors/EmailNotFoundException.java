package com.aj.pawatask.utils.errors;

public class EmailNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmailNotFoundException() {
        super("Email is not registered");
    }
}
