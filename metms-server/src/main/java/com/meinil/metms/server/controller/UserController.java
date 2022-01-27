package com.meinil.metms.server.controller;

import com.meinil.metms.commons.Path;
import com.meinil.metms.commons.Result;
import com.meinil.metms.commons.User;
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

    @PostMapping(Path.LOGIN)
    public Result login(@RequestBody User user) {
        return userService.login(user);
    }

    @PassToken(value = 0)
    @GetMapping(Path.USER_COUNT + "/{page}")
    public Result getUserCount(@PathVariable("page") int page) {
        return userService.getPage(page);
    }
}
