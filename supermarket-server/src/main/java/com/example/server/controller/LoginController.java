package com.example.server.controller;

import com.example.server.pojo.RespBean;
import com.example.server.pojo.User.UserLoginParam;
import com.example.server.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @PROJECT_NAME: supermarket
 * @DESCRIPTION:
 * @USER: 韩冰
 * @DATE: 2022/9/14 0014 16:22
 */

@RestController
@Api(tags = "LoginController")
public class LoginController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody UserLoginParam userLoginParam, HttpServletRequest request){
        return userService.login(userLoginParam.getUsername(),userLoginParam.getPassword(),userLoginParam.getCode(),request);
    }
}
