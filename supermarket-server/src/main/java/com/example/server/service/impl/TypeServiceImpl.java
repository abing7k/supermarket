package com.example.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.Utils.SqlUtils;
import com.example.server.mapper.TypeMapper;
import com.example.server.pojo.Goods.Type;
import com.example.server.pojo.Goods.TypeAdd;
import com.example.server.pojo.RespBean;
import com.example.server.pojo.User.User;
import com.example.server.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hanbing
 * @since 2022-09-14
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements ITypeService {

    @Autowired
    SqlUtils sqlUtils;

    @Autowired
    TypeMapper typeMapper;

    @Override
    public RespBean typeAdd(TypeAdd typeAdd, HttpServletRequest request) {

        if (sqlUtils.getIdByName((String) request.getSession().getAttribute("username")) != 1) {
            return RespBean.error("您没有权限添加商品类型");
        }

        if (getTypeByName(typeAdd.getName()) != null) {
            return RespBean.error("您已经添加过该商品类型");
        }

        if (!typeAdd.getFather().equals("")) {
            if (getTypeByName(typeAdd.getFather()) == null) {
                return RespBean.error("没有对应的父产品类型");
            }
        }


        if (typeInsert(typeAdd) > 0) {
            return RespBean.success("添加成功");
        } else {
            return RespBean.error("添加失败");
        }


    }

    @Override
    public RespBean typeChange(TypeAdd typeAdd, String rName, HttpServletRequest request) {

        if (sqlUtils.getIdByName((String) request.getSession().getAttribute("username")) != 1) {
            return RespBean.error("您没有权限更改商品类型");
        }

        if (getTypeByName(rName) != null) {
            return RespBean.error("此商品类型已存在");
        }

        if (!typeAdd.getFather().equals("")) {
            if (getTypeByName(typeAdd.getFather()) == null) {
                return RespBean.error("没有对应的父产品类型");
            }
        }


        if (change(typeAdd, rName) > 0) {
            return RespBean.success("更改成功");
        } else {
            return RespBean.error("更改失败");
        }
    }

    @Override
    public RespBean typeShow(String name, HttpServletRequest request) {

        if (sqlUtils.getIdByName((String) request.getSession().getAttribute("username")) != 1) {
            return RespBean.error("您没有权限查看商品类型");
        }

        return RespBean.success("查询成功", like(name));
    }

    @Override
    public RespBean typeDelete(String name, HttpServletRequest request) {

        if (sqlUtils.getIdByName((String) request.getSession().getAttribute("username")) != 1) {
            return RespBean.error("您没有权限更改商品类型");
        }

        Type type = getTypeByName(name);
        if (type == null) {
            return RespBean.error("删除目标不存在");
        }

        if (getIdByFather(type.getId()).size() > 0) { //查看子类长度, 若大于0,则代表有子类.不可删除
            return RespBean.error("有商品类型子类,请先删除商品子类");
        }

        if (deleteById(getIdByName(name)) > 0) {
            return RespBean.success("删除成功");
        }else {
            return RespBean.error("删除失败");
        }


    }

    private int deleteById(int id) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        return typeMapper.deleteByMap(map);
    }

    private List<Type> getIdByFather(int father) {
        return typeMapper.getTypeByFather(father);
    }

    private List<Type> like(String username) {
        QueryWrapper<Type> typeQueryWrapper = new QueryWrapper<>();
        typeQueryWrapper.like("name", username);
        return typeMapper.selectList(typeQueryWrapper);
    }

    private int typeInsert(TypeAdd typeAdd) {
        Type type = getType(typeAdd);
        type.setCreateTime(LocalDateTime.now());
        type.setDeleted(false);
        return typeMapper.insert(type);
    }


    private int change(TypeAdd typeAdd, String rName) {
        Type type = getType(typeAdd);
        type.setId(getIdByName(type.getName())); //设置Id,根据id更改信息
        if (!rName.equals("")) {
            type.setName(rName);
        }
        return typeMapper.updateById(type);
    }

    private Type getTypeById(int id) {
        return typeMapper.selectById(id);
    }

    private Type getType(TypeAdd typeAdd) {
        Type type = new Type();
        if (!typeAdd.getFather().equals("")) {
            type.setFather(getIdByName(typeAdd.getFather()));
        } else {
            type.setFather(0); //顶级商品父类为0
        }

        if (!typeAdd.getName().equals("")) {
            type.setName(typeAdd.getName());
        }

        if (!typeAdd.getDescription().equals("")) {
            type.setDescription(typeAdd.getDescription());
        }
        return type;
    }

    private Type getTypeByName(String name) {
        return typeMapper.getTypeByName(name);
    }

    private Integer getIdByName(String name) {
        return typeMapper.getIdByName(name);
    }


}
