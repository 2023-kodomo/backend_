package com.example.kodomoproject.global.security.jwt;

import com.example.kodomoproject.global.security.jwt.exception.GenerationFailedException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    @Value("${jwt.secret}")
    private String secretKey;

    public String accessToken(String email) {
        return createToken(email, "access", 7200L);
    }

    private String createToken(String email, String type, Long exp) {
        if (email == null) {
            throw GenerationFailedException.EXCEPTION;
        }

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date expiration = new Date(nowMillis + exp * 1000L);

        try {
            return Jwts.builder()
                    .claim("type", type)
                    .setSubject(email)
                    .setIssuedAt(now)
                    .setExpiration(expiration)
                    .signWith(SignatureAlgorithm.HS256, secretKey)
                    .compact();
        } catch (JwtException e) {
            throw GenerationFailedException.EXCEPTION;
        }
    }

}
