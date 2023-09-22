package com.example.kodomoproject.domain.auth.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class SignupRequest {

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$",
            message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "이름이 비어있습니다.")
    private String name;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]*$",
            message = "비밀번호 형식이 올바르지 않습니다.")
    private String password;
}
