package com.example.weighttraining.repository;

import com.example.weighttraining.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepostiory extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByRefreshUuid(String refreshUuid);
}
