package com.king.re0.service;

import com.king.re0.entity.UserTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<UserTokenEntity,Long> {
}
