package com.king.re0.service;

import com.king.re0.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity getByMobileIsAndPasswordIs(Long mobile, String password);

    UserEntity getByMobileIs(Long mobile);

    Optional<UserEntity> findByMobile(Long Long);
}
