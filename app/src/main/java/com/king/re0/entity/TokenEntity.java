package com.king.re0.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "token")
@Entity
@Data
public class TokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne(optional=false)
    @JoinColumn(name = "user_id",unique = true)
    private UserEntity userEntity;
    @Column(name = "token")
    private String token;
}
