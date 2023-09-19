package com.example.kodomoproject.domain.auth.service;

import com.example.kodomoproject.domain.auth.controller.dto.request.LoginRequest;
import com.example.kodomoproject.domain.auth.controller.dto.response.LoginResponse;
import com.example.kodomoproject.domain.auth.exception.PasswordNotMatchedException;
import com.example.kodomoproject.domain.user.entity.User;
import com.example.kodomoproject.domain.user.exception.UserNotFoundException;
import com.example.kodomoproject.domain.user.repository.UserRepository;
import com.example.kodomoproject.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    public LoginResponse execute(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw PasswordNotMatchedException.EXCEPTION;
        }
        String accessToken = jwtProvider.createAccessToken(request.getEmail());
        String refreshToken = jwtProvider.createRefreshToken(request.getEmail());

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
