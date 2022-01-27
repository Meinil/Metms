package com.meinil.metms.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

//@MapperScan("com.meinil.metms.server.mapper")
@SpringBootApplication
public class MetmsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetmsServerApplication.class, args);
    }

}
