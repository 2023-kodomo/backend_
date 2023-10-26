package com.example.kodomoproject.global.security.jwt;

import com.example.kodomoproject.domain.auth.controller.dto.response.TokenResponse;
import com.example.kodomoproject.domain.auth.entity.RefreshToken;
import com.example.kodomoproject.domain.auth.repository.RefreshTokenRepository;
import com.example.kodomoproject.domain.user.repository.UserRepository;
import com.example.kodomoproject.global.redis.RedisKey;
import com.example.kodomoproject.global.error.exception.InvalidDataException;
import com.example.kodomoproject.global.security.exception.JwtCreationFailedException;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Value("${jwt.secret}")
    private String secretKey;

    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer";

    public String createAccessToken(String email) {
        return createToken(email, "access", 7200L);
    }

    public String createRefreshToken(String email) {
        String token = createToken(email, "refresh", 60 * 60 * 60 * 720L);

        refreshTokenRepository.save(RefreshToken.builder()
                .token(token)
                .user(RedisKey.REFRESH.getKey() + email)
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
            Claims claims = Jwts.claims().setSubject(email);
            claims.put("type", type);
            return Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(now)
                    .setExpiration(expiration)
                    .signWith(SignatureAlgorithm.HS256, secretKey)
                    .compact();

        } catch (JwtException e) {
            throw JwtCreationFailedException.EXCEPTION;
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public Claims parseClaims(String token) throws JwtException {
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

    public String getEmail(Claims claims) {
        return claims.getSubject();
    }

    public TokenResponse reissue(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findById(token)
                .orElseThrow(() -> InvalidDataException.EXCEPTION);
        String email = userRepository.findByEmail(refreshToken.getUser()
                        .substring(RedisKey.REFRESH.getKey().length()))
                .orElseThrow(() -> InvalidDataException.EXCEPTION).getEmail();

        refreshTokenRepository.delete(refreshToken);

        return TokenResponse.builder()
                .accessToken(createAccessToken(email))
                .refreshToken(createRefreshToken(email))
                .build();
    }

    public String extractToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER);
        if(StringUtils.hasText(token) && token.startsWith(PREFIX))
            return token.substring(PREFIX.length());
        return null;
    }

}
