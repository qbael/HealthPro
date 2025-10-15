package com.healthpro.authservice.exception;

public class WrongPassWordException extends RuntimeException {
    public WrongPassWordException(String message) {
        super(message);
    }
}
