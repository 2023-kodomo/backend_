package com.example.kodomoproject.domain.auth.service;


import com.example.kodomoproject.domain.auth.controller.dto.request.ReissueRequest;
import com.example.kodomoproject.domain.auth.controller.dto.response.TokenResponse;
import com.example.kodomoproject.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReissueService {
    private final JwtProvider jwtProvider;

    public TokenResponse execute(ReissueRequest request) {
        return jwtProvider.reissue(request.getRefreshToken());
    }

}