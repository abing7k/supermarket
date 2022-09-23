package com.example.server.Utils;

import com.example.server.mapper.UserMapper;
import com.example.server.pojo.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @PROJECT_NAME: supermarket
 * @DESCRIPTION:
 * @USER: 韩冰
 * @DATE: 2022/9/22 0022 0:35
 */
@Component
public class SqlUtils {
    @Autowired
    UserMapper userMapper;

    public int getIdByName(String username) {
        User userByName = userMapper.getUserByName(username);
        if (userByName != null) {
            return userByName.getId();
        } else {
            return -1;
        }
    }
}
