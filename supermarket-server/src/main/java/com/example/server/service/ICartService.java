package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.Cart;
import com.example.server.pojo.RespBean;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hanbing
 * @since 2022-09-14
 */
public interface ICartService extends IService<Cart> {

    RespBean changeNumber(String name,int count,  HttpServletRequest request);

    RespBean addGoods(String name, int count, HttpServletRequest request);

    RespBean deleteGoods(String name, HttpServletRequest request);

    RespBean showCart(String name, HttpServletRequest request);
}
