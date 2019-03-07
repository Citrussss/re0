package com.king.re0.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Table(name = "user_collection")
@Entity
public class CollectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    public  UserEntity user;
    @JoinColumn(name = "memo_id")
    @ManyToOne(optional = false)
    private MemoEntity memo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectionEntity entity = (CollectionEntity) o;
        return Objects.equals(user, entity.user) &&
                Objects.equals(memo, entity.memo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, memo);
    }
}
