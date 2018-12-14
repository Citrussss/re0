package com.king.re0.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_dev")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "moblie")
    private Long mobile;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
//    @Temporal(TemporalType.TIME)
    private String password;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.REMOVE},mappedBy = "userEntity")
    private Set<MemoEntity> memoEntities;
    @JsonIgnore
    @OneToOne(optional=false, mappedBy="userEntity")
    public TokenEntity tokenEntity;
    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", mobile=" + mobile +
                ", name='" + name + '\'' +
                ", password=" + password +
                ", memoEntities=" + memoEntities +
                '}';
    }
}