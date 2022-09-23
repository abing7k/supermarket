package com.example.server.controller;


import com.example.server.pojo.RespBean;
import com.example.server.service.impl.CartServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hanbing
 * @since 2022-09-14
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartServiceImpl cartService;

    @ApiOperation("在购物车添加商品")
    @PostMapping("addGoods")
    public RespBean addGoods(String name, int count, HttpServletRequest request) {
        return cartService.addGoods(name, count, request);
    }

    @ApiOperation("修改商品数量")
    @PostMapping("changeNumber")
    public RespBean changeNumber(String name, int count, HttpServletRequest request) {
        return cartService.changeNumber(name, count, request);
    }

    @ApiOperation("删除购物车中的商品")
    @PostMapping("deleteGoods")
    public RespBean deleteGoods(String name, HttpServletRequest request) {
        return cartService.deleteGoods(name, request);
    }

    @ApiOperation("展示购物车商品列表")
    @PostMapping("showCart")
    public RespBean showCart(String name, HttpServletRequest request) {
        return cartService.showCart(name, request);
    }

}
