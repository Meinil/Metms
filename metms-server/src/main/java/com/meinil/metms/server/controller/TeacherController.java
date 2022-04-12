package com.meinil.metms.server.controller;

import com.meinil.metms.server.annotation.PassToken;
import com.meinil.metms.server.model.Plan;
import com.meinil.metms.server.model.dto.ExperienceDto;
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
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private PlanService planServiceImpl;

    @Autowired
    private UserService userServiceImpl;

    @Autowired
    private ExperienceService experienceService;

    @PassToken(value = 2)
    @PostMapping(  "/plan")
    public Result releasePlan(@RequestBody Plan plan) {
        return planServiceImpl.insertPlan(plan);
    }

    @PassToken(value = 2)
    @GetMapping("/student/{account}")
    public Result getAllStudent(@PathVariable String account) {
        return userServiceImpl.getAllStudent(account);
    }

    @PassToken(value = 2)
    @GetMapping("/plan/approval/pending/{account}")
    public Result getPendingPlan(@PathVariable String account) {
        return planServiceImpl.getPendingPlan(account);
    }

    @PassToken(value = 2)
    @PostMapping("/approval")
    public Result approval(@RequestBody ExperienceDto experience) {
        experienceService.updateExperience(experience);
        Result result = new Result();
        result.setMessage("更新成功");
        result.setCode(200);
        return result;
    }
}
