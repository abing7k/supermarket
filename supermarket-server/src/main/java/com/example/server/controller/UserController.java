package com.example.server.controller;


import com.example.server.pojo.RespBean;
import com.example.server.pojo.User.UserChange;
import com.example.server.pojo.User.UserRegist;
import com.example.server.service.IUserService;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @ApiOperation(value = "添加用户")
    @PostMapping("/addUser")
    public RespBean addUser(UserRegist userRegist, HttpServletRequest request){
        return userService.addUser(userRegist,request);
    }

    @ApiOperation(value = "修改用户")
    @PostMapping("/changeUser")
    public RespBean changeUser(UserChange userChange,HttpServletRequest request){
        return userService.changeUser(userChange,request);
    }

}
