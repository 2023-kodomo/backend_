package com.example.kodomoproject.domain.auth.exception;

import com.example.kodomoproject.global.error.exception.CustomException;
import com.example.kodomoproject.global.error.ErrorCode;

public class RoleNotMatchException extends CustomException {
    public static final CustomException EXCEPTION = new RoleNotMatchException();

    private RoleNotMatchException() {
        super(ErrorCode.FORBIDDEN);
    }
}
