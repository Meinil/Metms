package com.meinil.metms.client.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class Config {
    private static HashMap<String, Object> CONFIG;
    private final static String[] paths = new String[] {
        "cache",
        "cache/config.json"
    };

    static {
        createFile();
        try(FileInputStream in = new FileInputStream(paths[1])) {
            String cnf = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            CONFIG = JSON.parseObject(cnf, HashMap.class);
            if (CONFIG == null) {
                CONFIG = new HashMap<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 存储键值对
     * @param key 键
     * @param value 值
     */
    public static void put(String key, Object value) {
        CONFIG.put(key, value);
        try (FileOutputStream out = new FileOutputStream(paths[1])){
            out.write(JSON.toJSONString(CONFIG).getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取值
     * @param key 指定key
     * @return 存储的值
     */
    public static Object get(String key) {
        return CONFIG.get(key) == null ? "" : CONFIG.get(key);
    }
    public static <T> T get(String key, Class<T> clazz) {
        Object value = CONFIG.get(key);
        if (value instanceof JSONObject) {
            return ((JSONObject)CONFIG.get(key)).toJavaObject(clazz);
        }
        return (T)value;
    }

    private static void createFile() {
        for (String path : paths) {
            File file = new File(path);
            if (!file.exists()) {
                String name = file.getName();
                if (name.contains(".")) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    file.mkdirs();
                }
            }
        }
    }
}
