package com.king.re0.service;

import com.king.re0.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

    Optional<TokenEntity> findByToken(String token);
    Optional<TokenEntity> findByUserId(Long id);

}
