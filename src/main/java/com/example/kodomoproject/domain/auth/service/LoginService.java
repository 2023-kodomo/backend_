package com.example.kodomoproject.domain.auth.service;

import com.example.kodomoproject.domain.auth.controller.dto.request.LoginRequest;
import com.example.kodomoproject.domain.auth.controller.dto.response.TokenResponse;
import com.example.kodomoproject.domain.auth.exception.PasswordNotMatchedException;
import com.example.kodomoproject.domain.auth.exception.RoleNotMatchException;
import com.example.kodomoproject.domain.user.entity.User;
import com.example.kodomoproject.domain.user.entity.UserRole;
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

    public TokenResponse execute(LoginRequest request) {
        validateUser(request);

        String accessToken = jwtProvider.createAccessToken(request.getEmail());
        String refreshToken = jwtProvider.createRefreshToken(request.getEmail());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void validateUser(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw PasswordNotMatchedException.EXCEPTION;
        }

        validateRole(user);
    }

    private void validateRole(User user) {
        if (user.getRole()!= UserRole.USER) {
            throw RoleNotMatchException.EXCEPTION;
        }
    }


}
