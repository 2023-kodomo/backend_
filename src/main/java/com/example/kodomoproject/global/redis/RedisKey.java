package com.example.kodomoproject.global.redis;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RedisKey {
    REFRESH("REFRESH_"),
    EMAIL_AUTH("EMAIL_AUTH_");

    private final String key;
}