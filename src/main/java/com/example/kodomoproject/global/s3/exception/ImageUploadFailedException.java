package com.example.kodomoproject.global.s3.exception;

import com.example.kodomoproject.global.error.exception.CustomException;
import com.example.kodomoproject.global.error.ErrorCode;

public class ImageUploadFailedException extends CustomException {
    public static final CustomException EXCEPTION = new ImageUploadFailedException();

    private ImageUploadFailedException() {
        super(ErrorCode.IMAGE_UPLOAD_FAILED);
    }

}
