package com.aj.pawatask.utils.errors;

public class EmailNotFoundExcetion extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmailNotFoundExcetion() {
        super("Email is not registered");
    }
}
