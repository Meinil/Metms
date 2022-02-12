package com.meinil.metms.server;

import com.meinil.metms.server.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

class MetmsServerApplicationTests {
    @Autowired
    private UserMapper userMapper;

    public static void main(String[] args) {
    }

    @Test
    public static void contextLoads() {
        System.out.println(MetmsServerApplicationTests.class.getResource("/").toExternalForm());
    }

    private final static String[] paths = new String[] {
            "cache/test/cache.txt",
    };

    private static void createFile() {
        for (String path : paths) {
            File file = new File(path);
            try {
                System.out.println(file.getAbsolutePath());
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
