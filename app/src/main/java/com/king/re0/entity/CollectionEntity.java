package com.king.re0.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "user_collection")
@Entity
public class CollectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int collectId;
    @OneToOne(optional = false)
    @JoinColumn(name = "user_id",unique = true)
    public  UserEntity user;
    @JoinColumn(name = "memo_id",unique = true)
    @OneToOne(optional = false)
    private MemoEntity memo;
}
