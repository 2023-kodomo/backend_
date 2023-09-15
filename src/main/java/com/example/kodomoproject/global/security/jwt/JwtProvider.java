package com.example.kodomoproject.global.security.jwt;

import com.example.kodomoproject.global.security.jwt.exception.GenerationFailedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;
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

    public Claims parseClaims(String token) throws JwtException {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJwt(token).getBody();
    }

    public Authentication getAuthentication(String token) {
        try {
            Claims claims = parseClaims(token);
            UserDetails details = createAuthenticatedUserFromClaims(claims);
            return new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
        } catch (JwtException e) {
            return null;
        }
    }

    private UserDetails createAuthenticatedUserFromClaims(Claims claims) {
        String email = getEmail(claims);
        return new User(email, "", Collections.emptyList());
    }

    private String getEmail(Claims claims) {
        return claims.getSubject();
    }


}
