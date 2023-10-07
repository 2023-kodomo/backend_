package com.example.kodomoproject.domain.comment.exception;

import com.example.kodomoproject.global.error.exception.CustomException;
import com.example.kodomoproject.global.error.ErrorCode;

public class CommentNotFoundException extends CustomException {
    public static final CustomException EXCEPTION = new CommentNotFoundException();

    private CommentNotFoundException() {
        super(ErrorCode.COMMENT_NOT_FOUND);
    }
}
