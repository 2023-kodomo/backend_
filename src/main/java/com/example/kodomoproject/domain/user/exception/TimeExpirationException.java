package com.example.kodomoproject.domain.user.exception;

import com.example.kodomoproject.global.error.ErrorCode;
import com.example.kodomoproject.global.error.exception.CustomException;

public class TimeExpirationException extends CustomException {
    public static final CustomException EXCEPTION = new TimeExpirationException();

    private TimeExpirationException() {
        super(ErrorCode.TIME_EXPIRATION);
    }

}
