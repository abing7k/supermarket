package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.Goods.Goods;
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
public interface IGoodsService extends IService<Goods> {

    RespBean pay(String uName, String gName, HttpServletRequest request);
}
