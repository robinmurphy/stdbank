package com.sbg.automation.vending.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InvalidPaymentException extends RuntimeException {

    public InvalidPaymentException(String exception) {
        super(exception);
    }
}
