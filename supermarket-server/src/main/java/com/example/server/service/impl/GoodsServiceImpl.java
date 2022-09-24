package com.example.server.service.impl;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.Utils.SqlUtils;
import com.example.server.mapper.BillsMapper;
import com.example.server.mapper.CartMapper;
import com.example.server.mapper.GoodsMapper;
import com.example.server.pojo.Bills;
import com.example.server.pojo.Cart;
import com.example.server.pojo.Goods.Goods;
import com.example.server.pojo.RespBean;
import com.example.server.service.IGoodsService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Autowired
    SqlUtils sqlUtils;
    @Autowired
    CartMapper cartMapper;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    BillsMapper billsMapper;

    @Override
    public RespBean pay(String uName, String gName, HttpServletRequest request) {
        System.err.println(uName);
        if (sqlUtils.getIdByName((String) request.getSession().getAttribute("username")) != 2) {
            return RespBean.error("您不是收银员");
        }


        int uid = sqlUtils.getIdByName(uName);
        Integer gid = getGidByGname(gName);
        Cart cart = cartMapper.getCartByUidAndGid(uid, gid);

        if (cart == null) {
            return RespBean.error("购物车无此商品");
        }

        Goods goods = getGoodsById(gid);
        Integer count = cart.getCount();
        Long number = goods.getNumber();

        if (count <= 0) {
            return RespBean.error("购物车无此商品");
        }

        if (count > number) {
            return RespBean.error("数量不足,您购物车有" + count + "个,而库存只有" + number + "个");
        }

        if (count * goods.getPrice() * goods.getDiscount() > userService.getById(uid).getBalance()) {
            return RespBean.error("您的余额不足,请充值");
        }

        if (updateCartNumber(cart, count) > 0 && updateGoodsNumber(goods, count) > 0 &&
                addBill(uid, gid, count, count * goods.getPrice() * goods.getDiscount()) > 0) {
            return RespBean.success("购买成功");
        } else {
            return RespBean.error("购买失败");
        }

    }

    private int getGidByGname(String name) {
        return goodsMapper.getIdByName(name);
    }

    private Goods getGoodsById(int gid) {
        return goodsMapper.selectById(gid);
    }

    private int updateCartNumber(Cart cart, int count) {
        cart.setCount(cart.getCount() - count);
        return cartMapper.updateById(cart);
    }

    private int updateGoodsNumber(Goods goods, int count) {
        goods.setNumber(goods.getNumber() - count);
        return goodsMapper.updateById(goods);
    }

    private int addBill(int uid, int gid, int count, float price) {
        Bills bills = new Bills();
        bills.setUId(uid);
        bills.setGId(gid);
        bills.setCount(count);
        bills.setPrice(price);
        return billsMapper.insert(bills);
    }

}
