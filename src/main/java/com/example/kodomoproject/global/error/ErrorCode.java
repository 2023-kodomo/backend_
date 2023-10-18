package com.example.kodomoproject.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ALREADY_EXIST(400, "유저가 이미 존재합니다."),
    PASSWORD_NOT_MATCHES(400, "비밀번호가 일치하지 않습니다."),
    JWT_GENERATION_FAILED(400, "JWT 생성 실패"),
    INVALID_DATA(400, "잘못 된 데이터"),
    IMAGE_UPLOAD_FAILED(400, "이미지 업로드 실패"),
    IMAGE_DELETE_FAILED(400, "이미지 삭제 실패"),
    AUTH_CODE_NOT_MATCHES(400,"인증코드 불일치"),
    MAIL_CONFIRM_FAILED(400, "메일 전송 실패"),
    QR_GENERATION_FAILED(400, "QR 코드 생성 실패"),
    USER_NOT_MATCHES(400, "현재 사용자에겐 권한이 없습니다."),
    ALREADY_AUTHENTICATED_USER(400, "이미 인증된 사용자입니다."),

    TIME_EXPIRATION(401, "시간 만료"),

    FORBIDDEN(403, "권한이 없습니다."),

    IMAGE_NOT_FOUND(404, "이미지를 찾지 못했습니다."),
    COMMENT_NOT_FOUND(404, "댓글을 찾지 못했습니다."),
    PRODUCT_NOT_FOUND(404,"상품을 찾지 못했습니다."),
    USER_NOT_FOUND(404, "유저를 찾지 못했습니다.");

    private final int httpStatus;
    private final String message;
}