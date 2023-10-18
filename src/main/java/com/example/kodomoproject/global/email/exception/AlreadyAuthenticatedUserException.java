package com.example.kodomoproject.global.email.exception;

import com.example.kodomoproject.global.error.ErrorCode;
import com.example.kodomoproject.global.error.exception.CustomException;

public class AlreadyAuthenticatedUserException extends CustomException {
    public static final CustomException EXCEPTION = new AlreadyAuthenticatedUserException();

    private AlreadyAuthenticatedUserException() {
        super(ErrorCode.ALREADY_AUTHENTICATED_USER);
    }
}
