package com.example.kodomoproject.global.error.exception;

import com.example.kodomoproject.global.error.ErrorCode;

public class NoPermissionException extends CustomException {
    public static final CustomException EXCEPTION = new NoPermissionException();

    private NoPermissionException() {
        super(ErrorCode.EMAIL_NOT_AUTHENTICATED);
    }

}
