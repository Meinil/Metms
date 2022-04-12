package com.meinil.metms.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.meinil.metms.server.model.User;
import com.meinil.metms.server.model.vo.TeacherVo;
import com.meinil.metms.server.utils.Result;

import java.util.List;


/**
 * @Author Meinil
 * @Version 1.0
 */
public interface UserService extends IService<User> {
    public Result login(User user);

    public Result getPage(int page);

    public Result getUser(String account);

    public Result getAllStudent(String account);

    public List<TeacherVo> getAllTeacher();

    public boolean updateTeacher(String teacherId, String stuId);
}
