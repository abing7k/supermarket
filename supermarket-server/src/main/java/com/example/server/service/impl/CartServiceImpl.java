package com.example.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.Utils.SqlUtils;
import com.example.server.mapper.CartMapper;
import com.example.server.mapper.GoodsMapper;
import com.example.server.pojo.Cart;
import com.example.server.pojo.Goods.Goods;
import com.example.server.pojo.Goods.Type;
import com.example.server.pojo.RespBean;
import com.example.server.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hanbing
 * @since 2022-09-14
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {
    @Autowired
    SqlUtils sqlUtils;
    @Autowired
    CartMapper cartMapper;
    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public RespBean addGoods(String name, int count, HttpServletRequest request) {
        int uid = sqlUtils.getIdByName((String) request.getSession().getAttribute("username"));
        Integer gid = getIdByCname(name);
        if (gid == null) {
            return RespBean.error("添加的商品不存在");
        }

        if (cartMapper.getCartByUidAndGid(uid, gid) != null) { //已经添加过
            return RespBean.error("商品已经添加过");
        }

        if (insert(uid, gid, count) > 0) {
            return RespBean.success("添加购物车成功");
        } else {
            return RespBean.error("添加失败");
        }
    }

    @Override
    public RespBean changeNumber(String name, int count, HttpServletRequest request) {
        int uid = sqlUtils.getIdByName((String) request.getSession().getAttribute("username"));
        Integer gid = getGidByGname(name);
        Cart cart = cartMapper.getCartByUidAndGid(uid, gid);
        if (cart != null) {
            if (change(cart, count) > 0) {
                return RespBean.success("修改成功");
            } else {
                return RespBean.error("修改失败");
            }
        } else {
            return RespBean.error("修改失败,您的购物车中没有该商品");
        }
    }


    @Override
    public RespBean deleteGoods(String name, HttpServletRequest request) {
        int uid = sqlUtils.getIdByName((String) request.getSession().getAttribute("username"));
        Integer gid = getGidByGname(name);
        Cart cart = cartMapper.getCartByUidAndGid(uid, gid);
        if (cart != null) {
            if (delete(cart) > 0) {
                return RespBean.success("删除成功");
            } else {
                return RespBean.error("删除失败");
            }
        } else {
            return RespBean.error("删除失败,您的购物车中没有该商品");
        }
    }

    @Override
    public RespBean showCart(String name, HttpServletRequest request) {
        int uid = sqlUtils.getIdByName((String) request.getSession().getAttribute("username"));
        QueryWrapper<Cart> cartQueryWrapper = new QueryWrapper<>();
        cartQueryWrapper.like("u_id", uid);
        return RespBean.success("查看成功",cartMapper.selectList(cartQueryWrapper));
    }

    private int delete(Cart cart) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", cart.getId());
        return cartMapper.deleteByMap(map);
    }

    private int change(Cart cart, int count) {
        cart.setCount(count);
        return cartMapper.updateById(cart);
    }

    private Integer getIdByCname(String name) {
        return cartMapper.getIdByName(name);
    }

    private Integer getGidByGname(String name) {
        return goodsMapper.getIdByName(name);
    }

    private int insert(int uid, int gid, int count) {
        Cart cart = new Cart();
        cart.setUId(uid);
        cart.setGId(gid);
        cart.setCount(count);
        cart.setDeleted(0);
        cart.setCreateTime(LocalDateTime.now());
        return cartMapper.insert(cart);
    }
}
