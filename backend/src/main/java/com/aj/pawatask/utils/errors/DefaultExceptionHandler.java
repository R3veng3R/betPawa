package com.aj.pawatask.utils.errors;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(EmailNotFoundException.class)
    public String handleEmailNotFoundException(EmailNotFoundException exception) {
        return exception.getMessage();
    }
}
