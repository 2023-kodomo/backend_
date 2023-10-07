package com.example.kodomoproject.domain.auth.controller;

import com.example.kodomoproject.domain.auth.controller.dto.request.LoginRequest;
import com.example.kodomoproject.domain.auth.controller.dto.request.ReissueRequest;
import com.example.kodomoproject.domain.auth.controller.dto.request.SignupRequest;
import com.example.kodomoproject.domain.auth.controller.dto.response.TokenResponse;
import com.example.kodomoproject.domain.auth.service.LoginService;
import com.example.kodomoproject.domain.auth.service.ReissueService;
import com.example.kodomoproject.domain.auth.service.SignupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final SignupService signupService;
    private final LoginService loginService;
    private final ReissueService reissueService;

    @PostMapping("/signup")
    public void signup(@Valid @RequestBody SignupRequest request) {
        signupService.execute(request);
    }

    @PostMapping("/login")
    public TokenResponse login(@Valid @RequestBody LoginRequest request) {
        return loginService.execute(request);
    }

    @PostMapping("/reissue")
    public TokenResponse reissueToken(@Valid @RequestBody ReissueRequest request) {
        return reissueService.execute(request);
    }

}
