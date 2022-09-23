package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.Cart;
import com.example.server.pojo.Goods.Goods;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hanbing
 * @since 2022-09-14
 */
@Mapper
public interface CartMapper extends BaseMapper<Cart> {
    Integer getIdByName(String name);
    Cart getCartByUidAndGid(int uid, int gid);
}
