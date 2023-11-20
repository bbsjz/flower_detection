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
@ApiModel("花卉及介绍信息")
public class FlowerDto {
    @Id
    int id;

    @ApiModelProperty("英文名称")
    String name_en;

    @ApiModelProperty("中文名称")
    String name_zh;

    @ApiModelProperty("图片链接")
    String url;

    @ApiModelProperty("花卉信息描述")
    String info;
}