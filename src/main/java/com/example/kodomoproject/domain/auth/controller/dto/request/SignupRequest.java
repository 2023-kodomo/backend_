package com.example.kodomoproject.domain.auth.controller.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class SignupRequest {

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(dsm\\.hs\\.kr)$",
            message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Size(min = 2, max = 20, message = "이름 형식이 올바르지 않습니다.")
    private String name;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d!@#$%^&*()-=_+]*$",
            message = "비밀번호 형식이 올바르지 않습니다.")
    @Size(min = 8, max = 20)
    private String password;

}
