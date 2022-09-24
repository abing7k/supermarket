package com.example.server.controller;


import com.example.server.pojo.RespBean;
import com.example.server.service.impl.BillsServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hanbing
 * @since 2022-09-14
 */
@RestController
@RequestMapping("/bills")
public class BillsController {
    @Autowired
    BillsServiceImpl billsService;
    @ApiOperation("查看个人账单")
    @PostMapping("/showBillsByUserName")
    public RespBean showBillsByUserName(String name){
        return billsService.showBillsByUserName(name);
    }

    @ApiOperation("查看商品账单")
    @PostMapping("/showBillsByGoodsName")
    public RespBean showBillsByGoodsName(String name){
        return billsService.showBillsByGoodsName(name);
    }

    @ApiOperation("展示top10")
    @PostMapping("/showTop10")
    public RespBean showTop10(HttpServletRequest request){
        return billsService.showTop10(request);
    }
}
