package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.RespBean;
import com.example.server.pojo.User;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hanbing
 * @since 2022-09-14
 */
public interface IUserService extends IService<User> {

    RespBean login(String username, String password, String code, HttpServletRequest request);
}
