package com.example.kodomoproject.global.error;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Data
@Builder
public class ErrorResponse {
    private int status;
    private String message;

    public static ErrorResponse of(int status, String message) {
        return ErrorResponse.builder()
                .status(status)
                .message(message)
                .build();
    }

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode e) {
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(ErrorResponse.of(e.getHttpStatus(), e.getMessage()));
    }

    public static ResponseEntity<ErrorResponse> fromValidationException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        int status = HttpStatus.BAD_REQUEST.value();
        String message = fieldError != null ? fieldError.getDefaultMessage() : "Bad Request";

        return ResponseEntity
                .status(status)
                .body(ErrorResponse.of(status, message));
    }

}