package com.king.re0.dao;

import com.king.re0.entity.MemoEntity;
import com.king.re0.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface MemoRepository extends JpaRepository<MemoEntity, Long> {

    Optional<List<MemoEntity>> findAllByUserEntity(UserEntity userEntity);
//    Optional<List<MemoEntity>> findAllByUserId(Integer integer);

    @Query(value = "SELECT * FROM (SELECT * FROM memo  WHERE memo.latitude<200 AND longitude > 0) as result where (( ?1-result.latitude)*( ?1-result.latitude)+( ?2-result.longitude)*( ?2-result.longitude))< ?3*?3"
            , nativeQuery = true)
    Optional<List<MemoEntity>> findSSSS(BigDecimal longitude, BigDecimal latitude, BigDecimal distance);

}
