package com.king.re0.dao;

import com.king.re0.entity.CollectionEntity;
import com.king.re0.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CollectionRepository extends JpaRepository<CollectionEntity, Long> {
    Optional<Page<CollectionEntity>> findAllByUser(UserEntity userEntity, Pageable pageable);
//    Optional<CollectionEntity>> findByUserEntity(UserEntity userEntity);
    Optional<CollectionEntity> findByUser(UserEntity userEntity);

}
