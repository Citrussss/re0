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
    @Column(name = "userId")
    private Long userId;
    @Column(name = "token")
    private String token;
}
