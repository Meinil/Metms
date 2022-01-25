package com.meinil.metms.server.controller;

import com.meinil.metms.commons.Path;
import com.meinil.metms.commons.Result;
import com.meinil.metms.server.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Meinil
 * @Version 1.0
 */
@RestController
public class UserController {
    @PostMapping(Path.Login)
    public Result login(@RequestBody User user) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        return new Result();
    }
}
