package com.example.kodomoproject.global.security.exception;

import com.example.kodomoproject.global.error.exception.CustomException;
import com.example.kodomoproject.global.error.ErrorCode;

public class JwtCreationFailedException extends CustomException {
    public static final CustomException EXCEPTION = new JwtCreationFailedException();

    private JwtCreationFailedException() {
        super(ErrorCode.JWT_GENERATION_FAILED);
    }
}
