package com.example.server.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.Goods.Type;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hanbing
 * @since 2022-09-14
 */
@Mapper
public interface TypeMapper extends BaseMapper<Type> {
    Type getTypeByName(@Param("name") String name);

    Integer getIdByName(@Param("name") String name);

    List<Type> getTypeByFather(int father);
}
