package com.example.kodomoproject.global.security.jwt.exception;

import com.example.kodomoproject.global.exception.CustomException;
import com.example.kodomoproject.global.exception.error.ErrorCode;

public class JwtCreationFailedException extends CustomException {
    public static final CustomException EXCEPTION = new JwtCreationFailedException();

    private JwtCreationFailedException() {
        super(ErrorCode.CREATION_OR_GENERATION_ERROR_FAILED);
    }
}
