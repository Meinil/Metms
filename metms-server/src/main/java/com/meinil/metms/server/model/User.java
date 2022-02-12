package com.meinil.metms.server.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Meinil
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class User {
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

    public User(String id, String username, String account, Integer power) {
        this.id = id;
        this.username = username;
        this.account = account;
        this.power = power;
    }
}
