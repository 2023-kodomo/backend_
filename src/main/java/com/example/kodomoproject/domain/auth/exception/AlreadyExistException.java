package com.example.kodomoproject.domain.auth.exception;


import com.example.kodomoproject.global.error.exception.CustomException;
import com.example.kodomoproject.global.error.ErrorCode;

public class AlreadyExistException extends CustomException {
    public static final CustomException EXCEPTION = new AlreadyExistException();

    private AlreadyExistException() {
        super(ErrorCode.ALREADY_EXIST);
    }

}
