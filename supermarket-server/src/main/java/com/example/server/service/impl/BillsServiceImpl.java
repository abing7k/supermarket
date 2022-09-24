package com.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.Utils.SqlUtils;
import com.example.server.mapper.BillsMapper;
import com.example.server.mapper.GoodsMapper;
import com.example.server.mapper.TypeMapper;
import com.example.server.mapper.UserMapper;
import com.example.server.pojo.Bills;
import com.example.server.pojo.BillsShow;
import com.example.server.pojo.RespBean;
import com.example.server.service.IBillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hanbing
 * @since 2022-09-14
 */
@Service
public class BillsServiceImpl extends ServiceImpl<BillsMapper, Bills> implements IBillsService {

    @Autowired
    BillsMapper billsMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    TypeMapper typeMapper;
    @Autowired
    SqlUtils sqlUtils;

    @Override
    public RespBean showBillsByUserName(String name) {
        Integer uid = userMapper.getIdByName(name);
        if (uid == null) {
            return RespBean.error("没有此用户");
        }
        List<Bills> bills = billsMapper.getAllByUId(uid);
        List<BillsShow> billsShows = getBillsShows(bills);
        return RespBean.success("查询成功", billsShows);

    }


    @Override
    public RespBean showBillsByGoodsName(String name) {
        Integer gid = goodsMapper.getIdByName(name);
        if (gid == null) {
            return RespBean.error("没有此商品");
        }
        List<Bills> bills = billsMapper.getAllByUId(gid);
        List<BillsShow> billsShows = getBillsShows(bills);
        return RespBean.success("查询成功", billsShows);
    }

    @Override
    public RespBean showTop10(HttpServletRequest request) {
        if (sqlUtils.getIdByName((String) request.getSession().getAttribute("username")) != 2) {
            return RespBean.error("您不是收银员");
        }

        if (billsMapper.getTop().size() == 0){
            return RespBean.error("无结果");
        }

        return RespBean.success("查询成功",showTop(billsMapper.getTop()));
    }

    private Map showTop(Map map) {
        String gName = goodsMapper.getNameById((int) map.get("g_id"));
        map.remove("g_id");
        map.put("gName",gName);
        return map;
    }


    private List<BillsShow> getBillsShows(List<Bills> bills) {
        List<BillsShow> billsShows = new ArrayList<>();
        for (Bills bill : bills) {
            BillsShow billsShow = new BillsShow();
            Integer gId = bill.getGId();
            billsShow.setUName(userMapper.getNameById(bill.getUId()));
            billsShow.setGName(goodsMapper.getNameById(gId));
            billsShow.setTName(typeMapper.getNameById(gId));
            billsShow.setCount(bill.getCount());
            billsShow.setPrice(bill.getPrice());
            billsShow.setCreateTime(bill.getCreateTime());
            billsShows.add(billsShow);
        }
        return billsShows;
    }


}
