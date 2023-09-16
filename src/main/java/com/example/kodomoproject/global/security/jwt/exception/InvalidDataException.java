package com.example.kodomoproject.global.security.jwt.exception;


import com.example.kodomoproject.global.exception.CustomException;
import com.example.kodomoproject.global.exception.error.ErrorCode;

public class InvalidDataException extends CustomException {
    public static final CustomException EXCEPTION = new InvalidDataException();

    private InvalidDataException() {
        super(ErrorCode.INVALID_DATA);
    }
}
