package com.example.kodomoproject.global.security.jwt;

import com.example.kodomoproject.global.security.jwt.exception.InvalidDataException;
import com.example.kodomoproject.global.security.jwt.exception.JwtCreationFailedException;
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

    @Value("secret")
    private String secretKey;

    public String createAccessToken(String email) {
        return createToken(email, "access", 7200000L);
    }

    public String createRefreshToken(String email) {
        return createToken(email, "refresh", 720000L);
    }

    private String createToken(String email, String type, Long exp) {
        if (email == null || type == null || exp == null) {
            throw InvalidDataException.EXCEPTION;
        }

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date expiration = new Date(nowMillis + exp * 1000L);

        try {
            return Jwts.builder()
                    .claim("type", type)
                    .setIssuedAt(now)
                    .setSubject(email)
                    .setExpiration(expiration)
                    .signWith(SignatureAlgorithm.HS256, secretKey)
                    .compact();
        } catch (JwtException e) {
            throw JwtCreationFailedException.EXCEPTION;
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    private Claims parseClaims(String token) throws JwtException {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public Authentication getAuthentication(String token) {
        try {
            Claims claim = parseClaims(token);
            UserDetails details = createAuthenticatedUserFromClaims(claim);
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
