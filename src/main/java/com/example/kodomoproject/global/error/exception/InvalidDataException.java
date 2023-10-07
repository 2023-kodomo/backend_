package com.example.kodomoproject.global.error.exception;


import com.example.kodomoproject.global.error.ErrorCode;

public class InvalidDataException extends CustomException {
    public static final CustomException EXCEPTION = new InvalidDataException();

    private InvalidDataException() {
        super(ErrorCode.INVALID_DATA);
    }
}
