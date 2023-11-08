package com.project.server.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Proxy(lazy = false)
@ApiModel("用户")
@Entity
public class User {
    @Id
            @ApiModelProperty("用户名/id")
    String name;

    @ApiModelProperty("用户密码")
    String password;
}
