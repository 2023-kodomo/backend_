package com.example.kodomoproject.domain.auth.exception;

import com.example.kodomoproject.global.error.exception.CustomException;
import com.example.kodomoproject.global.error.ErrorCode;

public class PasswordNotMatchedException extends CustomException {
    public static final CustomException EXCEPTION = new PasswordNotMatchedException();

    private PasswordNotMatchedException() {
        super(ErrorCode.PASSWORD_NOT_MATCHES);
    }

}
