package com.example.server.controller;


import com.example.server.pojo.Goods.TypeAdd;
import com.example.server.pojo.RespBean;
import com.example.server.service.ITypeService;
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
@RequestMapping("/type")
public class TypeController {

    @Autowired
    ITypeService typeService;

    @ApiOperation(value = "添加类型")
    @PostMapping("typeAdd")
    public RespBean typeAdd(TypeAdd typeAdd, HttpServletRequest request) {
        return typeService.typeAdd(typeAdd, request);
    }

    @ApiOperation(value = "修改类型")
    @PostMapping("typeChange")
    public RespBean typeChange(TypeAdd typeAdd, String Rname, HttpServletRequest request) {
        return typeService.typeChange(typeAdd, Rname, request);
    }

    @ApiOperation(value = "查询类型")
    @PostMapping("typeShow")
    public RespBean typeShow(String name, HttpServletRequest request) {
        return typeService.typeShow(name, request);
    }

    @ApiOperation(value = "删除类型")
    @PostMapping("typeDelete")
    public RespBean typeDelete(String name, HttpServletRequest request) {
        return typeService.typeDelete(name, request);
    }
}
