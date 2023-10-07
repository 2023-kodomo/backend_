package com.example.kodomoproject.global.email.controller;

import com.example.kodomoproject.domain.auth.controller.dto.response.EmailVerifyResponse;
import com.example.kodomoproject.global.email.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/send/{email}")
    public void emailConfirm(@Email @PathVariable String email) throws MessagingException, UnsupportedEncodingException {
        emailService.sendAuthCode(email);
    }

    @GetMapping("/{email}/verify/{authCode}")
    public EmailVerifyResponse verifyEmail(@PathVariable String authCode, @Email @PathVariable String email) {
        return emailService.verifyEmail(authCode, email);
    }

    @PostMapping("/reissue/{email}")
    public void reissueAuthCode(@Email @PathVariable String email) {
        emailService.reissue(email);
    }
}
