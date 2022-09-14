package com.example.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author hanbing
 * @since 2022-09-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Goods对象", description="")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商品所属的类型id")
    @TableField("t_id")
    private Integer tId;

    @ApiModelProperty(value = "商品剩余数量")
    private Long number;

    @ApiModelProperty(value = "商品单价")
    private Float price;

    @ApiModelProperty(value = "商品折扣")
    private Float discount;

    @ApiModelProperty(value = "1-正常，0-下架（库存数量为0了）")
    private Integer state;

    @ApiModelProperty(value = "是否被删除(1代表删除)")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "删除时间")
    @TableField("modified_time")
    private LocalDateTime modifiedTime;


}
