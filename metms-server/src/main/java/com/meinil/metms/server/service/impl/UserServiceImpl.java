package com.meinil.metms.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meinil.metms.server.model.vo.TeacherVo;
import com.meinil.metms.server.service.UserService;
import com.meinil.metms.server.utils.Result;
import com.meinil.metms.server.mapper.UserMapper;
import com.meinil.metms.server.model.User;
import com.meinil.metms.server.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Meinil
 * @Version 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private TokenUtils utils;

    @Override
    public Result login(User user){
        User u = baseMapper.getUser(user);
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

    @Override
    public Result getPage(int page) {
        Result result = new Result();
        result.setCode(200);
        result.setMessage("ok");
        if (page == 1) {
            result.put("total", baseMapper.getTotalCount());
        }
        result.put("users", baseMapper.getUsers((page - 1) * 10));
        return result;
    }

    @Override
    public Result getUser(String account) {
        User user = baseMapper.getUserByStudentAccount(account);
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

    @Override
    public Result getAllStudent(String account) {
        List<User> students = baseMapper.getStudents(account);
        Result result = new Result(200);
        result.put("students", students);
        return result;
    }

    @Override
    public List<TeacherVo> getAllTeacher() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("id", "username", "description");
        wrapper.eq("power", 2);
        List<User> users = baseMapper.selectList(wrapper);
        ArrayList<TeacherVo> teacherVo = new ArrayList<>();
        users.forEach(user -> {
            TeacherVo teacher = new TeacherVo();
            teacher.setId(user.getId());
            teacher.setUsername(user.getUsername());
            teacher.setDescription(user.getDescription());
            teacherVo.add(teacher);
        });
        return teacherVo;
    }

    @Override
    public boolean updateTeacher(String teacherId, String stuId) {
        return baseMapper.updateTeacher(teacherId, stuId);
    }
}
