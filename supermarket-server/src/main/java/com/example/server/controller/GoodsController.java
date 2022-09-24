package com.example.server.controller;


import com.example.server.pojo.Goods.GoodsAdd;
import com.example.server.pojo.RespBean;
import com.example.server.service.impl.GoodsServiceImpl;
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
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsServiceImpl goodsService;

    @ApiOperation("付款")
    @PostMapping("/payGoods")
    public RespBean payGoods(String uName, String gName, HttpServletRequest request) {
        return goodsService.pay(uName, gName, request);
    }

    @ApiOperation("添加商品")
    @PostMapping("/addGoods")
    public RespBean addGoods(GoodsAdd goodsAdd, HttpServletRequest request) {
        return goodsService.addGoods(goodsAdd, request);
    }

    @ApiOperation("修改商品")
    @PostMapping("/updateGoods")
    public RespBean updateGoods(GoodsAdd goodsAdd, String rGName, HttpServletRequest request) {
        return goodsService.updateGoods(goodsAdd, rGName, request);
    }

    @ApiOperation("删除商品")
    @PostMapping("/deleteGoods")
    public RespBean deleteGoods(String gName, HttpServletRequest request) {
        return goodsService.deleteGoods(gName, request);
    }

    @ApiOperation("查看商品")
    @PostMapping("/showGoods")
    public RespBean showGoods(String gName, HttpServletRequest request) {
        return goodsService.showGoods(gName, request);
    }

}
