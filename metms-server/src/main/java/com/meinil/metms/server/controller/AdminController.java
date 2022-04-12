package com.meinil.metms.server.controller;

import com.meinil.metms.server.utils.Result;
import com.meinil.metms.server.model.User;
import com.meinil.metms.server.annotation.PassToken;
import com.meinil.metms.server.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Meinil
 * @Version 1.0
 */
@RestController
public class AdminController {
    @Autowired
    private UserServiceImpl userServiceImpl;


    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        return userServiceImpl.login(user);
    }

    @PassToken(value = 0)
    @GetMapping(  "/admin/page/{page}")
    public Result getUserCount(@PathVariable("page") int page) {
        return userServiceImpl.getPage(page);
    }
}
