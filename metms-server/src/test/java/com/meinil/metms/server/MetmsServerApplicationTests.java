package com.meinil.metms.server;

import com.alibaba.fastjson.JSON;
import com.meinil.metms.server.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MetmsServerApplicationTests {

    @Test
    public void contextLoads() {
        User user = new User();
        user.setUsername("445");
        user.setPassword("1233");
        String s = JSON.toJSONString(user);
        System.out.println(s);
    }
}
