package com.king.re0.dao;

import com.king.re0.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> getByMobileIsAndPasswordIs(Long mobile, String password);

    Optional<UserEntity> getByMobileIs(Long mobile);

    Optional<UserEntity> findByMobile(Long Long);
}
