package com.example.kodomoproject.domain.qr.exception;


import com.example.kodomoproject.global.error.exception.CustomException;
import com.example.kodomoproject.global.error.ErrorCode;

public class QrGenerationFailedException extends CustomException {
    public static final CustomException EXCEPTION = new QrGenerationFailedException();

    private QrGenerationFailedException() {
        super(ErrorCode.QR_GENERATION_FAILED);
    }
}
