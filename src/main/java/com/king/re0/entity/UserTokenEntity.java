package com.king.re0.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "userToken")
@Entity
@Data
public class UserTokenEntity {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    @Column(name = "mobile")
    private Long mobile;
    @Column(name = "token")
    private String token;
}
