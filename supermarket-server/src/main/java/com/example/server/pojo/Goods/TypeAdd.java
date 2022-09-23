package com.example.server.pojo.Goods;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @PROJECT_NAME: supermarket
 * @DESCRIPTION:
 * @USER: 韩冰
 * @DATE: 2022/9/22 0022 0:22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TypeAdd对象", description="")
public class TypeAdd {
    @ApiModelProperty(value = "类型名称")
    private String name;

    @ApiModelProperty(value = "父产品名称,没有代表是最顶级商品")
    private String father;

    @ApiModelProperty(value = "描述")
    private String description;

}
