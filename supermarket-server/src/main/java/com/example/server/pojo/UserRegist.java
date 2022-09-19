package com.example.server.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @PROJECT_NAME: supermarket
 * @DESCRIPTION:
 * @USER: 韩冰
 * @DATE: 2022/9/19 0019 18:14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "UserRegist对象", description = "")
public class UserRegist {
    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "用户密码")
    private String pwd;

    @ApiModelProperty(value = "角色名称")
    private int rid;

    @ApiModelProperty(value = "用户电话")
    private String tel;

    @ApiModelProperty(value = "用户余额")
    private Float balance;

}
