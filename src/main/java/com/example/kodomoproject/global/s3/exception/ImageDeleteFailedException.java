package com.example.kodomoproject.global.s3.exception;

import com.example.kodomoproject.global.error.exception.CustomException;
import com.example.kodomoproject.global.error.ErrorCode;

public class ImageDeleteFailedException extends CustomException {
    public static final CustomException EXCEPTION = new ImageDeleteFailedException();

    private ImageDeleteFailedException() {
        super(ErrorCode.IMAGE_DELETE_FAILED);
    }
}
