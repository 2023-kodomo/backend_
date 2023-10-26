package com.example.kodomoproject.domain.auth.controller.dto.response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RefreshResponse {

    private String accessToken;
}
