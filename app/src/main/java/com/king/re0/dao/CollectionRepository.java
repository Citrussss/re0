package com.king.re0.dao;

import com.king.re0.entity.CollectionEntity;
import com.king.re0.entity.MemoEntity;
import com.king.re0.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CollectionRepository extends JpaRepository<CollectionEntity, Long> {
    Optional<List<CollectionEntity>> findAllByUser(UserEntity userEntity, Pageable pageable);
    Optional<List<CollectionEntity>> findAllByUser(UserEntity userEntity);

    //    Optional<CollectionEntity>> findByUserEntity(UserEntity userEntity);
    Optional<CollectionEntity> findByUser(UserEntity userEntity);
    Optional<CollectionEntity> findByUserAndMemo(UserEntity userEntity, MemoEntity memoEntity);

}
