package com.example.kodomoproject.global.email.exception;

import com.example.kodomoproject.global.error.exception.CustomException;
import com.example.kodomoproject.global.error.ErrorCode;

public class MailSendException extends CustomException {
    public static final CustomException EXCEPTION = new MailSendException();

    private MailSendException() {
        super(ErrorCode.MAIL_CONFIRM_FAILED);
    }

}
