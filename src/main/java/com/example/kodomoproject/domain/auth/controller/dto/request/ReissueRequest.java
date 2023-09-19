package com.example.kodomoproject.domain.auth.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReissueRequest {
    private String refreshToken;
}
