package com.meinil.metms.server.controller;

import com.meinil.metms.server.annotation.PassToken;
import com.meinil.metms.server.model.Experience;
import com.meinil.metms.server.service.ExperienceService;
import com.meinil.metms.server.service.PlanService;
import com.meinil.metms.server.service.UserService;
import com.meinil.metms.server.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Meinil
 * @Version 1.0
 */
@RestController
public class StudentController {
    @Autowired
    private UserService userService;

    @Autowired
    private PlanService planService;

    @Autowired
    private ExperienceService experienceService;

    @PassToken(value = 3)
    @GetMapping("/student/teacher/{account}")
    public Result getUser(@PathVariable String account) {
        return userService.getUser(account);
    }

    /**
     * 查询学生的计划
     * @param id 学生的id
     */
    @PassToken(value = 3)
    @GetMapping("/student/plan/{id}")
    public Result getPlan(@PathVariable String id) {
        return planService.getPlan(id);
    }

    @PassToken(value = 3)
    @PostMapping("/student/experience")
    public Result insertExperience(@RequestBody Experience experience) {
        return experienceService.insertExperience(experience);
    }
}
