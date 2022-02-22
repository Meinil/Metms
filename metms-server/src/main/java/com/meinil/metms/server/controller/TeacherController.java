package com.meinil.metms.server.controller;

import com.meinil.metms.server.annotation.PassToken;
import com.meinil.metms.server.model.Plan;
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
public class TeacherController {
    @Autowired
    private PlanService planService;

    @Autowired
    private UserService userService;

    @PassToken(value = 2)
    @PostMapping(  "/teacher/plan")
    public Result releasePlan(@RequestBody Plan plan) {
        return planService.insertPlan(plan);
    }

    @PassToken(value = 2)
    @GetMapping("/teacher/student/{account}")
    public Result getAllStudent(@PathVariable String account) {
        return userService.getAllStudent(account);
    }

    @PassToken(value = 2)
    @GetMapping("/teacher/plan/approval/pending/{account}")
    public Result getPendingPlan(@PathVariable String account) {
        return planService.getPendingPlan(account);
    }
}
