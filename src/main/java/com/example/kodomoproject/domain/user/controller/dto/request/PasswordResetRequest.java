package com.example.kodomoproject.domain.user.controller.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
public class PasswordResetRequest {

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]*$",
            message = "비밀번호 형식이 올바르지 않습니다.")
    private String newPassword;

}
