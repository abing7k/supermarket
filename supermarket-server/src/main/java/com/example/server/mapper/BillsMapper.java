package com.example.server.mapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.Bills;
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
public interface BillsMapper extends BaseMapper<Bills> {
    ArrayList<Bills> getAllByUId(@Param("uId") Integer uId);

    ArrayList<Bills> getAllByGId(@Param("gId") Integer gId);
    Map getTop();
}
