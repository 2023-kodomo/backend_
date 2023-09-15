package com.example.kodomoproject.domain.auth.service;

import com.example.kodomoproject.domain.auth.controller.dto.request.LoginRequest;
import com.example.kodomoproject.domain.auth.controller.dto.response.LoginResponse;
import com.example.kodomoproject.domain.user.entity.User;
import com.example.kodomoproject.domain.user.exception.UserNotFoundException;
import com.example.kodomoproject.domain.user.repository.UserRepository;
import com.example.kodomoproject.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public LoginResponse execute(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        String accessToken = jwtProvider.accessToken(user.getEmail());
        return LoginResponse.builder()
                .accessToken(accessToken)
                .build();
    }

}
