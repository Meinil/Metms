package com.meinil.metms.client.model;

import com.meinil.metms.client.config.CacheSave;

import java.util.Properties;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class User implements CacheSave {
    private String id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 权限
     */
    private Integer power;

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public Integer getPower() {
        return power;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    @Override
    public void save(Properties properties) {
        properties.setProperty("id", id);
        properties.setProperty("username", username);
        properties.setProperty("account", account);
        properties.setProperty("power", String.valueOf(power));
    }
}
