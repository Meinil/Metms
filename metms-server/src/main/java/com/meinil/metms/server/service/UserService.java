package com.meinil.metms.server.service;

import com.meinil.metms.server.utils.Result;
import com.meinil.metms.server.mapper.UserMapper;
import com.meinil.metms.server.model.User;
import com.meinil.metms.server.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (page == 1) {
            result.put("total", userMapper.getTotalCount());
        }
        result.put("users", userMapper.getUsers((page - 1) * 10));
        return result;
    }

    public Result getUser(String account) {
        User user = userMapper.getUserByStudentAccount(account);
        Result result;
        if (user == null) {
            result = new Result(400);
            result.setMessage("该用户暂无导师");
        } else {
            result = new Result(200);
            result.put("mentor", user);
            result.setMessage("ok");
        }
        return result;
    }

    public Result getAllStudent(String account) {
        List<User> students = userMapper.getStudents(account);
        Result result = new Result(200);
        result.put("students", students);
        return result;
    }
}
