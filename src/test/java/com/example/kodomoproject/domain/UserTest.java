package com.example.kodomoproject.domain;

import com.example.kodomoproject.domain.user.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

class UserTest {

    @Test
    @DisplayName("사용자 회원가입 테스트")
    void 회원가입() {
        Date now = new Date();

        User user = User.builder()
                .name("테스트")
                .email("email@.com")
                .password("비밀번호")
                .createdDate(now)
                .build();

        Assertions.assertEquals("테스트", user.getName());
        Assertions.assertEquals("email@.com", user.getEmail());
        Assertions.assertEquals("비밀번호", user.getPassword());
        Assertions.assertEquals(now, user.getCreatedDate());
    }

}