package com.example.kodomoproject.global.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ALREADY_EXIST(400, "유저가 이미 존재합니다."),
    PASSWORD_NOT_MATCHES(400, "비밀번호가 일치하지 않습니다."),
    CREATION_OR_GENERATION_ERROR_FAILED(400, "생성 실패"),
    INVALID_DATA(400, "잘못된 데이터"),

    COMMENT_NOT_FOUND(404, "댓글을 찾지 못했습니다."),
    PRODUCT_NOT_FOUND(404,"상품을 찾지 못했습니다."),
    USER_NOT_FOUND(404, "유저를 찾지 못했습니다.");

    private final int httpStatus;
    private final String message;
}