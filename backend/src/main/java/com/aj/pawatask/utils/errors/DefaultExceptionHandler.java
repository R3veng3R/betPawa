package com.aj.pawatask.utils.errors;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(EmailNotFoundExcetion.class)
    public String handleEmailNotFoundException(EmailNotFoundExcetion exception) {
        return exception.getMessage();
    }
}
