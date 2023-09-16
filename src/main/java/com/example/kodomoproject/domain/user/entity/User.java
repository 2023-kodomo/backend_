package com.example.kodomoproject.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

    @Getter
    @Setter
    @NoArgsConstructor
    @Document
    public class User {

        @Id
        private String id;

        @NotBlank
        private String email;

        @NotBlank
        private String name;

        @NotBlank
        @JsonIgnore
        private String password;

    @Builder
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
