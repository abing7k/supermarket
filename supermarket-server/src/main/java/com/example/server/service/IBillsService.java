package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.Bills;
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
public interface IBillsService extends IService<Bills> {

    RespBean showBillsByUserName(String name);

    RespBean showBillsByGoodsName(String name);

    RespBean showTop10(HttpServletRequest request);
}
