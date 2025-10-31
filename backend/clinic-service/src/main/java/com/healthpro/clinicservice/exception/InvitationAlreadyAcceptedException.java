package com.healthpro.clinicservice.exception;

public class InvitationAlreadyAcceptedException extends RuntimeException {
    public InvitationAlreadyAcceptedException(String message) {
        super(message);
    }
}
