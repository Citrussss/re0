package com.king.re0.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.king.re0.base.error.ApiException;
import io.netty.util.internal.StringUtil;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user_dev")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @Column(name = "moblie")
    private Long mobile;
    @Column(name = "name")
    private String name;
    @JsonIgnore
    @Column(name = "password")
    private String password;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE}, mappedBy = "userEntity")
    private Set<MemoEntity> memoEntities;
    @JsonIgnore
    @OneToOne(optional = false, mappedBy = "userEntity")
    public TokenEntity tokenEntity;
    @Column(name = "avatar")
    private String avatar;

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", mobile=" + mobile +
                ", name='" + name + '\'' +
                ", password=" + password +
                '}';
    }

    public void checkRegistrationLegal() {
        if (StringUtil.isNullOrEmpty(getPassword())) throw new ApiException(10, "密码不能为空");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(mobile, that.mobile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mobile);
    }

}