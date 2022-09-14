package com.example.server.pojo;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@ApiModel(value="Bills对象", description="")
public class Bills implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "账单id")
    private Integer id;

    @ApiModelProperty(value = "用户id")
    @TableField("u_id")
    private Integer uId;

    @ApiModelProperty(value = "商品id")
    @TableField("g_id")
    private Integer gId;

    @ApiModelProperty(value = "购买数量")
    private Integer count;

    @ApiModelProperty(value = "总价格")
    private Float price;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("modified_time")
    private LocalDateTime modifiedTime;


}
