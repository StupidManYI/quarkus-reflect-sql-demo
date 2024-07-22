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
public class IdParam {

    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
