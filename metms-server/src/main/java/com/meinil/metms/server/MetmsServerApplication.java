package com.meinil.metms.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class MetmsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetmsServerApplication.class, args);
    }

}
