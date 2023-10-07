package com.example.kodomoproject.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@NoArgsConstructor
@Document
public class User {

    @Id
    private String id;

    private String email;

    private String name;

    @JsonIgnore
    private String password;

    private String profileImage;

    private UserRole role = UserRole.GUEST;

    @Builder
    public User(String name, String email, String password, String profileImage) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.profileImage = profileImage;
    }

    public void imageUpload(String profileImage) {
        this.profileImage = profileImage;
    }

    public void addRole(UserRole role) {
        this.role = role;
    }

    public void changePassword(String password) {
        this.password = password;
    }
}
