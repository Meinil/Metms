package com.meinil.metms.server.controller;

import com.meinil.metms.server.model.Plan;
import com.meinil.metms.server.service.PlanService;
import com.meinil.metms.server.utils.Result;
import com.meinil.metms.server.model.User;
import com.meinil.metms.server.annotation.PassToken;
import com.meinil.metms.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Meinil
 * @Version 1.0
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PlanService planService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        return userService.login(user);
    }

    @PassToken(value = 0)
    @GetMapping(  "/admin/page/{page}")
    public Result getUserCount(@PathVariable("page") int page) {
        return userService.getPage(page);
    }

    @PassToken(value = 2)
    @PostMapping(  "/teacher/plan")
    public Result releasePlan(@RequestBody Plan plan) {
        return planService.insertPlan(plan);
    }
}
