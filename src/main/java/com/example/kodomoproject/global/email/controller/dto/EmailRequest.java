package com.example.kodomoproject.global.email.controller.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailRequest {

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(dsm\\.hs\\.kr)$",
            message = "이메일 형식이 올바르지 않습니다.")
    private String email;

}
