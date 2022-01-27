package com.meinil.metms.server;

import com.meinil.metms.server.mapper.UserMapper;
import com.meinil.metms.commons.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MetmsServerApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        Class<?> clazz = Autowired.class;
        System.out.println(clazz.equals(Autowired.class));
    }
}
