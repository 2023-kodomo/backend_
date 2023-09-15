package com.example.kodomoproject.domain.auth.service;

import com.example.kodomoproject.domain.auth.controller.dto.request.SignupRequest;
import com.example.kodomoproject.domain.auth.exception.UserAlreadyExistException;
import com.example.kodomoproject.domain.user.entity.User;
import com.example.kodomoproject.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class SignupService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private void execute(SignupRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> UserAlreadyExistException.EXCEPTION);

        userRepository.save(User.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .createdDate(new Date())
                .build());
    }

}

