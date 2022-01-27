package com.meinil.metms.server.service;

import com.meinil.metms.commons.Result;
import com.meinil.metms.server.mapper.UserMapper;
import com.meinil.metms.commons.User;
import com.meinil.metms.server.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Meinil
 * @Version 1.0
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TokenUtils utils;

    public Result login(User user){
        User u = userMapper.getUser(user);
        Result result = new Result();
        if (u != null) {
            result.setCode(200);
            result.setMessage("ok");
            result.put("user", u);
            result.put("token", utils.getToken(u));
        } else {
            result.setCode(100);
            result.setMessage("密码或账号错误");
        }
        return result;
    }

    public Result getPage(int page) {
        Result result = new Result();
        result.setCode(200);
        result.setMessage("ok");
        if (page == 0) {
            result.put("total", userMapper.getTotalCount());
        } else {
            result.put("users", userMapper.getUsers((page - 1) * 10));
        }
        return result;
    }
}
