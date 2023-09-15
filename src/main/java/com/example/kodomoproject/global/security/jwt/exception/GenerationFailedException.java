package com.example.kodomoproject.global.security.jwt.exception;

import com.example.kodomoproject.global.exception.CustomException;
import com.example.kodomoproject.global.exception.error.ErrorCode;

public class GenerationFailedException extends CustomException {
    public static final CustomException EXCEPTION = new GenerationFailedException();

    private GenerationFailedException() {
        super(ErrorCode.CREATION_OR_GENERATION_ERROR_FAILED);
    }

}
