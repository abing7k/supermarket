package com.example.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.User.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author hanbing
 * @since 2022-09-14
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    User getUserByName(String name);

    Integer getIdByName(@Param("name") String name);

    String getNameById(@Param("id") Integer id);

}
