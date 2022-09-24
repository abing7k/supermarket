package com.example.server.pojo.Goods;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @PROJECT_NAME: supermarket
 * @DESCRIPTION:
 * @USER: 韩冰
 * @DATE: 2022/9/24 0024 14:24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GoodsAdd对象", description="")
public class GoodsAdd {
    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商品所属的类型")
    private String TName;

    @ApiModelProperty(value = "商品数量")
    private Long number;

    @ApiModelProperty(value = "商品单价")
    private Float price;

    @ApiModelProperty(value = "商品折扣")
    private Float discount;
}
