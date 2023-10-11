package com.example.kodomoproject.domain.product.exception;

import com.example.kodomoproject.global.error.ErrorCode;
import com.example.kodomoproject.global.error.exception.CustomException;

public class UserNotMatchException extends CustomException {
    public static final CustomException EXCEPTION = new UserNotMatchException();

    private UserNotMatchException() {
        super(ErrorCode.USER_NOT_MATCHES);
    }

}
