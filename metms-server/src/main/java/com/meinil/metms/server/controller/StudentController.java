package com.meinil.metms.server.controller;

import com.meinil.metms.server.annotation.PassToken;
import com.meinil.metms.server.model.Experience;
import com.meinil.metms.server.service.impl.ExperienceServiceImpl;
import com.meinil.metms.server.service.impl.PlanServiceImpl;
import com.meinil.metms.server.service.impl.UserServiceImpl;
import com.meinil.metms.server.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Meinil
 * @Version 1.0
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private PlanServiceImpl planServiceImpl;

    @Autowired
    private ExperienceServiceImpl experienceServiceImpl;

    @PassToken(value = 3)
    @GetMapping("/teacher/{account}")
    public Result getUser(@PathVariable String account) {
        return userServiceImpl.getUser(account);
    }

    /**
     * 查询学生的计划
     * @param id 学生的id
     */
    @PassToken(value = 3)
    @GetMapping("/plan/{id}")
    public Result getPlan(@PathVariable String id) {
        return planServiceImpl.getPlan(id);
    }

    @PassToken(value = 3)
    @PostMapping("/experience")
    public Result insertExperience(@RequestBody Experience experience) {
        return experienceServiceImpl.insertExperience(experience);
    }

    @PassToken(value = 3)
    @GetMapping("/teacherList")
    public Result getAllTeacher() {
        Result result = new Result();
        result.setCode(200);
        result.setMessage("请求成功");
        result.put("teachers", userServiceImpl.getAllTeacher());
        return result;
    }

    @PassToken(value = 3)
    @GetMapping("/{teacherId}/{stuId}")
    public Result updateTeacher(@PathVariable String teacherId,
                                @PathVariable String stuId) {
        boolean flag = userServiceImpl.updateTeacher(teacherId, stuId);
        Result result = new Result();
        if (flag) {
            result.setCode(200);
            result.setMessage("请求成功");
        } else {
            result.setCode(400);
            result.setMessage("更新失败");
        }
        return result;
    }
}
