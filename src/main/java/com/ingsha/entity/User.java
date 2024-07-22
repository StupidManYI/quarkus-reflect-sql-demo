package com.ingsha.entity;

import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * @ClassName User
 * @Description TODO
 * @Author Martin Yi
 * @Date 2024/7/22 22:41
 * @Version 1.0
 */
@RegisterForReflection
public class User {

    Long id;

    String name;

    String avatar;

    String bgImg;

    Integer age;

    public User(Long id, String name, String avatar, String bgImg, Integer age) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.bgImg = bgImg;
        this.age = age;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBgImg() {
        return bgImg;
    }

    public void setBgImg(String bgImg) {
        this.bgImg = bgImg;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
