package com.example.kodomoproject.domain.auth.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenResponse {
    private String userId;
    private String accessToken;
    private String refreshToken;

}
