package com.example.kodomoproject.global.security.jwt;

import com.example.kodomoproject.domain.auth.controller.dto.response.TokenResponse;
import com.example.kodomoproject.domain.auth.entity.RefreshToken;
import com.example.kodomoproject.domain.auth.repository.RefreshTokenRepository;
import com.example.kodomoproject.domain.user.repository.UserRepository;
import com.example.kodomoproject.global.security.jwt.exception.InvalidDataException;
import com.example.kodomoproject.global.security.jwt.exception.JwtCreationFailedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Value("${jwt.secret}")
    private String secretKey;

    public String createAccessToken(String email) {
        return createToken(email, "access", 720L);
    }

    public String createRefreshToken(String email) {
        String token = createToken("", "refresh", 720000L);

        refreshTokenRepository.save(RefreshToken.builder()
                .token(token)
                .user(email)
                .build());
        return token;
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
        if (email.isBlank()){
            throw InvalidDataException.EXCEPTION;
        }
        return new User(email, "", Collections.emptyList());
    }

    private String getEmail(Claims claims) {
        return claims.getSubject();
    }

    public TokenResponse reissue(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findById(token)
                .orElseThrow(() -> InvalidDataException.EXCEPTION);
        String email = userRepository.findByEmail(refreshToken.getUser())
                .orElseThrow(() -> InvalidDataException.EXCEPTION).getEmail();

        refreshTokenRepository.delete(refreshToken);

        return TokenResponse.builder()
                .accessToken(createAccessToken(email))
                .refreshToken(createRefreshToken(email))
                .build();
    }

}
