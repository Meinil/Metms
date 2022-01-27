package com.meinil.metms.client.config;

import com.meinil.metms.client.component.PromptBox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class CacheConfig {
    private final static Properties CONFIG = new Properties();
    private static String[] filePath = {
      "cache",
      "cache/config.properties"
    };
    private static Pattern pattern = Pattern.compile("\\.");
    static {
        FileInputStream in = null;
        try {
            createFile();
            in = new FileInputStream(new File(filePath[1]));
            CONFIG.load(in);
        } catch (IOException e) {
            PromptBox.setShow("创建本地缓存文件失败", PromptBox.ERROR);
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 缓存信息
     * @param key   键
     * @param value 值
     */
    public static void save(String key, Object value) {
        CONFIG.put(key, value);
        syncFile();
    }

    public static void save(CacheSave save) {
        save.save(CONFIG);
        syncFile();
    }

    /**
     * 修改同步到文件
     */
    private static void syncFile() {
        try(FileOutputStream out = new FileOutputStream(filePath[1])) {
            CONFIG.store(out, "系统信息");
        } catch (IOException e) {
            PromptBox.setShow("保存失败", PromptBox.ERROR);
            e.printStackTrace();
        }
    }

    /**
     * 获取值
     * @param key 键
     * @return 值
     */
    public static String get(String key) {
        return CONFIG.getProperty(key) == null ? "" : CONFIG.getProperty(key);
    }

    /**
     * 创建本地缓存文件夹及文件
     * @throws IOException
     */
    private static void createFile() throws IOException {
        for (String path : filePath) {
            File file = new File(path);
            if (!file.exists()) {
                Matcher matcher = pattern.matcher(path);
                if (matcher.find()) {
                    file.createNewFile();
                } else {
                    file.mkdirs();
                }
            }
        }
    }
}
