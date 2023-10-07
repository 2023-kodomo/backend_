package com.example.kodomoproject.domain.user.controller;

import com.example.kodomoproject.domain.user.controller.dto.request.PasswordResetRequest;
import com.example.kodomoproject.domain.user.controller.dto.response.PasswordResetResponse;
import com.example.kodomoproject.domain.user.service.PasswordResetService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;


@RestController
@RequestMapping("/password-reset")
@RequiredArgsConstructor
public class PasswordResetController {
    private final PasswordResetService service;

    @PostMapping("/send")
    public void sendPasswordResetMail() throws MessagingException, UnsupportedEncodingException {
        service.sendMail();
    }

    @GetMapping("/v/{token}")
    public PasswordResetResponse verifyToken(@PathVariable String token) {
        return service.verifyResponse(token);
    }

    @PostMapping("/r/{token}")
    public void resetPassword(@PathVariable String token,
                              @RequestBody PasswordResetRequest request) {
        service.changePassword(token, request);
    }

}
