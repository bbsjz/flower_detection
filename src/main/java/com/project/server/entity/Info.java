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
@ApiModel("花卉信息")
@Entity
public class Info {

    @Id
            @ApiModelProperty("花卉id")
    int id;

    @ApiModelProperty("花卉信息描述")
    String info;
}
