package com.example.kodomoproject.domain.auth.exception;

import com.example.kodomoproject.global.error.exception.CustomException;
import com.example.kodomoproject.global.error.ErrorCode;

public class UserAlreadyExistException extends CustomException {
    public static final CustomException EXCEPTION = new UserAlreadyExistException();

    private UserAlreadyExistException() {
        super(ErrorCode.ALREADY_EXIST);
    }
}
