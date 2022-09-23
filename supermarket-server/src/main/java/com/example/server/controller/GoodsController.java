package com.example.server.controller;


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
        return goodsService.pay(uName, gName,request);
    }
}
