package com.example.kodomoproject.domain.auth.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class ReissueRequest {
    @NotBlank(message = "리프레시 토큰이 비어있습니다.")
    private String refreshToken;

}
