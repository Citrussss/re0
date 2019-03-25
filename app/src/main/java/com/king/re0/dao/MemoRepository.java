package com.king.re0.dao;

import com.king.re0.entity.MemoEntity;
import com.king.re0.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemoRepository extends JpaRepository<MemoEntity, Long> {

    Optional<List<MemoEntity>> findAllByUserEntity(UserEntity userEntity);

    Optional<List<MemoEntity>> findAllByUserEntityAndCreateTimeBetween(UserEntity userEntity, long startTime, long endTime);

    @Query(value = "SELECT *\n" +
            "FROM (SELECT * FROM memo  WHERE memo.latitude<200 AND longitude > 0) as result where round(6378.138*2*asin(sqrt(pow(sin( (?2*pi()/180-result.latitude*pi()/180)/2),2)+cos(?2*pi()/180)*cos(result.latitude*pi()/180)* pow(sin( (?1*pi()/180-result.longitude*pi()/180)/2),2)))*1000)<?3"
            , nativeQuery = true)
    Optional<List<MemoEntity>> findByLocation2(Double longitude, Double latitude, Double distance);

    //TODO 等待 加入 左查询 表user_collection
}
