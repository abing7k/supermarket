package com.example.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.Goods.Goods;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author hanbing
 * @since 2022-09-14
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
    Integer getIdByName(@Param("name") String name);

    Goods getGoodsByName(String name);

    String getNameById(@Param("id") Integer id);
}
