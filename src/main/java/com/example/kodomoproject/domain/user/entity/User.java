package com.example.kodomoproject.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
    @Setter
    @NoArgsConstructor
    @Document
    public class User {

        @Id
        private String id;

        @NotBlank
        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$",
                message = "이메일 형식이 올바르지 않습니다.")
        private String email;

        @NotBlank
        private String name;

        @NotBlank
        @JsonIgnore
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]*$",
                message = "비밀번호 형식이 올바르지 않습니다.")
        private String password;

    @Builder
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
