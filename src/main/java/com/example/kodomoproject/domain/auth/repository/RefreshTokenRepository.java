package com.example.kodomoproject.domain.auth.repository;

import com.example.kodomoproject.domain.auth.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}

