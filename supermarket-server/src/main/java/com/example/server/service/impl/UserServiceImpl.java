package com.example.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.Utils.SqlUtils;
import com.example.server.config.security.JwtTokenUtils;
import com.example.server.mapper.UserMapper;
import com.example.server.pojo.RespBean;
import com.example.server.pojo.User.User;
import com.example.server.pojo.User.UserChange;
import com.example.server.pojo.User.UserRegist;
import com.example.server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    SqlUtils sqlUtils;

    @Value("${jwt.tokenHead}")
    private String tokenHead;
    UserDetails userDetails;

    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request) {
        String captcha = (String) request.getSession().getAttribute("captcha");
        if (StringUtils.isEmpty(code) || !captcha.equalsIgnoreCase(code)) {
            return RespBean.error("验证码出入错误,请重新驶入");
        }
        //登录
        userDetails = userDetailsService.loadUserByUsername(username);
        if (null == userDetails || !passwordEncoder.matches(password, userDetails.getPassword())) {
            return RespBean.error("用户名或密码不正确");
        }
        if (!userDetails.isEnabled()) {
            return RespBean.error("账号被禁用,请联系管理员");
        }

        //更新Security用户登录对象
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //生成token
        String token = jwtTokenUtils.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return RespBean.success("登录成功", tokenMap);
    }

    @Override
    public RespBean addUser(UserRegist userRegist, HttpServletRequest request) {
        System.out.println("getUsername" + request.getSession().getAttribute("username"));
        if (sqlUtils.getIdByName((String) request.getSession().getAttribute("username")) != 1) {
            return RespBean.error("您没有权限创建用户");
        }

        if (userRegist.getRid() == 1) { //只能创建会员和收银员
            return RespBean.error("您没有权限创建管理员");
        }

        if (sqlUtils.getIdByName(userRegist.getName()) != -1) {
            return RespBean.error("用户名重复,请重新创建");
        }


        User user = new User();
        user.setName(userRegist.getName());
        user.setRId(userRegist.getRid());
        user.setPwd(passwordEncoder.encode(userRegist.getPwd())); //对密码加密
        user.setTel(userRegist.getTel());
        user.setBalance(userRegist.getBalance());
        user.setEnabled(true);
        user.setDeleted(0);

        if (userInsert(user) > 0) {
            return RespBean.success("创建成功");
        } else {
            return RespBean.error("创建失败");
        }
    }

    @Override
    public RespBean changeUser(UserChange userChange, HttpServletRequest request) {
        if (sqlUtils.getIdByName((String) request.getSession().getAttribute("username")) == 1) { //信息只能被管理员修改

            if (sqlUtils.getIdByName(userChange.getName()) == -1) {
                return RespBean.error("无此用户");
            }

            if (sqlUtils.getIdByName(userChange.getRname()) != -1) {
                return RespBean.error("用户名已被占用");
            }

            if (change(userChange) > 0) {
                return RespBean.success("修改成功");
            } else {
                return RespBean.error("修改失败");
            }
        } else {
            return RespBean.error("无权修改");
        }
    }

    @Override
    public RespBean deleteUser(String username) {
        int id = sqlUtils.getIdByName(username);
        if (id != -1) {
            if (deleteById(id) > 0) {
                return RespBean.success("删除成功");
            }else {
                return RespBean.error("删除失败");
            }
        }else {
            return RespBean.error("未找到该用户");
        }
    }

    @Override
    public RespBean selectUser(String username,HttpServletRequest request) {
        if (sqlUtils.getIdByName((String) request.getSession().getAttribute("username")) == 1) {
            return RespBean.success("查看成功",like(username));
        }else {
            return RespBean.error("无权查看");
        }
    }

    @Override
    //充值会员
    public RespBean recharge(String username, float money, HttpServletRequest request) {
        if (sqlUtils.getIdByName((String) request.getSession().getAttribute("username")) == 1) {

            if (sqlUtils.getIdByName(username) == -1) {
                return RespBean.error("无此用户");
            }

            if (rechargeMoney(username,money)>0) {
                return RespBean.success("充值成功");
            }else {
                return RespBean.error("充值失败");
            }
        }else {
            return RespBean.error("无权充值");
        }
    }

    private int rechargeMoney(String username, float money){
        int id = sqlUtils.getIdByName(username);
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        User user = userMapper.selectById(id); //查出要充值的对象
        user.setBalance(user.getBalance()+money); //充值
        return userMapper.updateById(user);
    }

    private List<User> like(String username){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like("name", username);
        return userMapper.selectList(userQueryWrapper);
    }

    private int deleteById(int id) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        return userMapper.deleteByMap(map);
    }

    private int userInsert(User user) {
        return userMapper.insert(user);
    }

//    private int sqlUtils.getIdByName(String username) {
//        User userByName = userMapper.getUserByName(username);
//        if (userByName != null) {
//            return userByName.getId();
//        } else {
//            return -1;
//        }
//    }

    private int change(UserChange userChange) {
        User user = new User();
        int id = sqlUtils.getIdByName(userChange.getName());

        user.setId(id);
        user.setName(userChange.getRname());
        user.setPwd(passwordEncoder.encode(userChange.getPwd()));
        if (!userChange.getTel().equals("")) {
            user.setTel(userChange.getTel());
        }
        user.setIntegral(userChange.getIntegral());
        user.setBalance(user.getBalance());

        return userMapper.updateById(user);
    }


}
