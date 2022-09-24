package com.example.server.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @PROJECT_NAME: supermarket
 * @DESCRIPTION:
 * @USER: 韩冰
 * @DATE: 2022/9/24 0024 16:41
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BillsShow对象", description="")
public class BillsShow {

    @ApiModelProperty(value = "购买人")
    private String uName;

    @ApiModelProperty(value = "商品名称")
    private String gName;

    @ApiModelProperty(value = "商品所属的类型名称")
    private String tName;

    @ApiModelProperty(value = "购买数量")
    private Integer count;

    @ApiModelProperty(value = "总价格")
    private Float price;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

}
