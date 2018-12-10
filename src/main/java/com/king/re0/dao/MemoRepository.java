package com.king.re0.dao;

import com.king.re0.entity.MemoEntity;
import com.king.re0.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemoRepository extends JpaRepository<MemoEntity, Long> {

    Optional<List<MemoEntity>> findAllByUserEntity(UserEntity userEntity);
}
