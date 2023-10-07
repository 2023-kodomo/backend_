package com.example.kodomoproject.domain.auth.service;

import com.example.kodomoproject.domain.auth.controller.dto.request.SignupRequest;
import com.example.kodomoproject.domain.auth.exception.AlreadyExistException;
import com.example.kodomoproject.domain.user.entity.User;
import com.example.kodomoproject.domain.user.repository.UserRepository;
import com.example.kodomoproject.global.s3.DefaultProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignupService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void execute(SignupRequest request) {
        if (userRepository.findByName(request.getName()) .isPresent() ||
            userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw AlreadyExistException.EXCEPTION;
        }

        userRepository.save(User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .profileImage(DefaultProfile.DEFAULT_PROFILE)
                .build());
    }
}
