package com.king.re0.dao;

import com.king.re0.entity.TokenEntity;
import com.king.re0.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

    Optional<TokenEntity> findByToken(String token);
    Optional<TokenEntity> findByUserEntity(UserEntity userEntity);

}
