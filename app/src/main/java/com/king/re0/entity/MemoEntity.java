package com.king.re0.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vividsolutions.jts.geom.Point;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "memo")
@Data
public class MemoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "create_time")
    private Long createTime;
    @Column(name = "update_time")
    private Long updateTime;
    @Column(name = "content")
    private String content;
    @Column(name = "type")
    private String type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    @Column(columnDefinition = "geometry(Point)")
    private Point point;
    @Column(name = "longitude")
    private Double longitude;
    @Column(name = "latitude")
    private Double latitude;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemoEntity that = (MemoEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(content, that.content) &&
                Objects.equals(type, that.type) &&
                Objects.equals(userEntity, that.userEntity) &&
                Objects.equals(point, that.point) &&
                Objects.equals(longitude, that.longitude) &&
                Objects.equals(latitude, that.latitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, updateTime, content, type, userEntity, point, longitude, latitude);
    }

    @Override
    public String toString() {
        return "MemoEntity{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", userEntity=" + userEntity +
                ", point=" + point +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
