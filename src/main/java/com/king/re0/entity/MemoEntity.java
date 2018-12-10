package com.king.re0.entity;


import com.vividsolutions.jts.geom.Point;

import javax.persistence.*;

@Entity
@Table(name = "memo")
public class MemoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "create_time")
    private Long creatTime;
    @Column(name = "update_time")
    private Long updateTime;
    @Column(name = "content")
    private String content;
    @Column(name = "type")
    private String type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    @Column()
    private Point point;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Long creatTime) {
        this.creatTime = creatTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MemoController{" +
                "id=" + id +
                ", creatTime=" + creatTime +
                ", updateTime=" + updateTime +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", userEntity=" + userEntity +
                '}';
    }
}
