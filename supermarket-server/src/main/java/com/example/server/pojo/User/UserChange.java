package com.example.server.pojo.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @PROJECT_NAME: supermarket
 * @DESCRIPTION:
 * @USER: 韩冰
 * @DATE: 2022/9/20 0020 13:16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UserChange对象", description="")
public class UserChange {
    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "修改后的名字")
    private String rname;

    @ApiModelProperty(value = "用户密码")
    private String pwd;

    @ApiModelProperty(value = "用户电话")
    private String tel;

    @ApiModelProperty(value = "消费积分")
    private Float integral;

    @ApiModelProperty(value = "用户余额")
    private Float balance;
}
