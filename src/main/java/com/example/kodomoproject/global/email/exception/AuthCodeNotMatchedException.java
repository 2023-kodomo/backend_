package com.example.kodomoproject.global.email.exception;

import com.example.kodomoproject.global.error.exception.CustomException;
import com.example.kodomoproject.global.error.ErrorCode;

public class AuthCodeNotMatchedException extends CustomException {
    public static final CustomException EXCEPTION = new AuthCodeNotMatchedException();

    private AuthCodeNotMatchedException() {
        super(ErrorCode.AUTH_CODE_NOT_MATCHES);
    }

}
