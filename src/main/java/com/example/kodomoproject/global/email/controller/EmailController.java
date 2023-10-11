package com.example.kodomoproject.global.email.controller;

import com.example.kodomoproject.domain.auth.controller.dto.response.EmailVerifyResponse;
import com.example.kodomoproject.global.email.controller.dto.EmailRequest;
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

    @PostMapping("/send")
    public void emailConfirm(@RequestBody EmailRequest request) throws MessagingException, UnsupportedEncodingException {
        emailService.sendAuthCode(request);
    }

    @GetMapping("/verify/{authCode}")
    public EmailVerifyResponse verifyEmail(@PathVariable String authCode,
                                           @RequestBody EmailRequest request) {
        return emailService.verifyEmail(authCode, request);
    }

    @PostMapping("/reissue")
    public void reissueAuthCode(@RequestBody EmailRequest request) {
        emailService.reissue(request);
    }
}
