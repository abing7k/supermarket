package com.example.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.Utils.SqlUtils;
import com.example.server.mapper.BillsMapper;
import com.example.server.mapper.CartMapper;
import com.example.server.mapper.GoodsMapper;
import com.example.server.mapper.TypeMapper;
import com.example.server.pojo.Bills;
import com.example.server.pojo.Cart;
import com.example.server.pojo.Goods.Goods;
import com.example.server.pojo.Goods.GoodsAdd;
import com.example.server.pojo.Goods.Type;
import com.example.server.pojo.Goods.TypeAdd;
import com.example.server.pojo.RespBean;
import com.example.server.service.IGoodsService;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

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
    @Autowired
    TypeMapper typeMapper;

    @Override
    public RespBean pay(String uName, String gName, HttpServletRequest request) {
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

    @Override
    public RespBean addGoods(GoodsAdd goodsAdd, HttpServletRequest request) {

        if (sqlUtils.getIdByName((String) request.getSession().getAttribute("username")) != 2) {
            return RespBean.error("您不是收银员");
        }

        if (getGidByGname(goodsAdd.getName()) != null) {
            return RespBean.error("您已经添加过该商品了");
        }

        if (getTypeByName(goodsAdd.getTName()) == null) {
            return RespBean.error("没有对应的父产品类型");
        }

        if (goodsInsert(goodsAdd) > 0) {
            return RespBean.success("添加成功");
        } else {
            return RespBean.error("添加失败");
        }

    }

    @Override
    public RespBean updateGoods(GoodsAdd goodsAdd, String rGName, HttpServletRequest request) {
        if (sqlUtils.getIdByName((String) request.getSession().getAttribute("username")) != 2) {
            return RespBean.error("您不是收银员");
        }

        if (goodsAdd.getName().equals("") || getGidByGname(goodsAdd.getName()) == null) {
            return RespBean.error("该商品不存在");
        }

        if (!goodsAdd.getTName().equals("")) {
            if (getTypeByName(goodsAdd.getTName()) == null) {
                return RespBean.error("该商品类型不存在");
            }
        }

        if (!rGName.equals("")) {
            if (getGidByGname(rGName) != null) {
                return RespBean.error("该商品已存在");
            }
        }


        if (goodUpdate(goodsAdd, rGName) > 0) {
            return RespBean.success("修改成功");
        } else {
            return RespBean.error("修改失败");
        }
    }


    @Override
    public RespBean deleteGoods(String gName, HttpServletRequest request) {
        if (sqlUtils.getIdByName((String) request.getSession().getAttribute("username")) != 2) {
            return RespBean.error("您不是收银员");
        }

        if (gName.equals("") || getGidByGname(gName) == null) {
            return RespBean.error("该商品不存在");
        }
        if (goodDelete(gName) > 0) {
            return RespBean.success("删除成功");
        } else {
            return RespBean.error("删除失败");
        }

    }


    @Override
    public RespBean showGoods(String gName, HttpServletRequest request) {
        if (sqlUtils.getIdByName((String) request.getSession().getAttribute("username")) != 2) {
            return RespBean.error("您不是收银员");
        }

        return RespBean.success("查看成功",like(gName));

    }

    private int goodDelete(String gName) {
        return goodsMapper.deleteById(getGidByGname(gName));
    }

    private List<Goods> like(String name){
        QueryWrapper<Goods> goodsQueryWrapper = new QueryWrapper<>();
        goodsQueryWrapper.like("name", name);
        return goodsMapper.selectList(goodsQueryWrapper);
    }

    private int goodUpdate(GoodsAdd goodsAdd, String rGName) {
        Goods goods = new Goods();
        if (!rGName.equals("")) {
            goods.setName(rGName);
        }
        goods.setId(getGidByGname(goodsAdd.getName()));
        goods.setNumber(goodsAdd.getNumber());
        goods.setPrice(goodsAdd.getPrice());
        goods.setDiscount(goodsAdd.getDiscount());
        return goodsMapper.updateById(goods);
    }

    private Goods getGoodsByName(String name) {
        return goodsMapper.getGoodsByName(name);
    }

    private int goodsInsert(GoodsAdd goodsAdd) {
        Goods goods = getGoods(goodsAdd);
        return goodsMapper.insert(goods);
    }

    private Goods getGoods(GoodsAdd goodsAdd) {
        Goods goods = new Goods();
        goods.setName(goodsAdd.getName());
        goods.setTId(getTypeByName(goodsAdd.getTName()).getId());
        goods.setDiscount(goodsAdd.getDiscount());
        goods.setNumber(goodsAdd.getNumber());
        goods.setPrice(goodsAdd.getPrice());
        goods.setEnabled(1);
        goods.setDeleted(false);
        goods.setCreateTime(LocalDateTime.now());
        return goods;
    }

    private Integer getGidByGname(String name) {
        return goodsMapper.getIdByName(name);
    }

    private Type getTypeByName(String name) {
        return typeMapper.getTypeByName(name);
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
