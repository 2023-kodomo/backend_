package com.example.kodomoproject.domain.user.service;

import com.example.kodomoproject.domain.user.controller.dto.request.PasswordResetRequest;
import com.example.kodomoproject.domain.user.controller.dto.response.PasswordResetResponse;
import com.example.kodomoproject.domain.user.entity.User;
import com.example.kodomoproject.domain.user.exception.TimeExpirationException;
import com.example.kodomoproject.domain.user.repository.UserRepository;
import com.example.kodomoproject.domain.user.service.facade.UserFacade;
import com.example.kodomoproject.global.email.exception.MailSendException;
import com.example.kodomoproject.global.email.service.EmailService;
import com.example.kodomoproject.global.error.exception.InvalidDataException;
import com.example.kodomoproject.global.security.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class PasswordResetService {
    private final UserFacade userFacade;
    private final JwtProvider jwtProvider;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    // 링크 만들기 /user/password-reset/액세스토큰
    // 만들어서 메일보내기
    // url 에 붙은 토큰 검증하기

    @Value("${app.url}")
    private String defaultURL;

    private final UserRepository userRepository;

    private String makeURL(String email) {
        String token = jwtProvider.createAccessToken(email);

        return defaultURL + "/user/password-reset/v/" + token;
    }

    public void sendMail() throws MessagingException, UnsupportedEncodingException { // url 을 만들어 메일을 보낸다 !
        User user = userFacade.getUser();  // 로그인된 사용자가 비밀번호 재설정을 할 때
        String email = user.getEmail();

        String url = makeURL(email);

        emailService.sendPasswordReset(email, url);
    }

    public PasswordResetResponse verifyResponse(String token) { // 토큰으로 인증을 한다.
        if (verify(token)) {
            return PasswordResetResponse.builder()
                    .user(getUserEmail(token))
                    .message("인증에 성공했어요")
                    .build();
        } else {
            throw TimeExpirationException.EXCEPTION;
        }
    }

    private boolean verify(String token) {
        return jwtProvider.validateToken(token);
    }

    private String getUserEmail(String token) {
        Claims claims = jwtProvider.parseClaims(token);
        return jwtProvider.getEmail(claims);
    }

    public void changePassword(String token, PasswordResetRequest request) {
        if (verify(token)) {
            String email = getUserEmail(token);
            User user = userFacade.getUserByEmail(email);

            String newPassword = passwordEncoder.encode(request.getNewPassword());

            user.changePassword(newPassword);
            userRepository.save(user);

            try {
                emailService.sendSuccessPasswordReset(email);
            } catch (MessagingException e) {
                throw MailSendException.EXCEPTION;
            }
        } else {
            throw InvalidDataException.EXCEPTION;
        }
    }

}
