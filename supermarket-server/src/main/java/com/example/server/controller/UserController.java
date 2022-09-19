package com.example.server.controller;


import com.example.server.pojo.RespBean;
import com.example.server.pojo.User;
import com.example.server.pojo.UserRegist;
import com.example.server.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation(value = "添加会员")
    @PostMapping("/login")
    public RespBean addUser(UserRegist userRegist){
        return userService.addUser(userRegist);
    }
}
