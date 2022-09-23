package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.Goods.Type;
import com.example.server.pojo.Goods.TypeAdd;
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
public interface ITypeService extends IService<Type> {

    RespBean typeAdd(TypeAdd typeAdd, HttpServletRequest request);

    RespBean typeChange(TypeAdd typeAdd, String rname, HttpServletRequest request);

    RespBean typeShow(String name,HttpServletRequest request);

    RespBean typeDelete(String name,HttpServletRequest request);
}
