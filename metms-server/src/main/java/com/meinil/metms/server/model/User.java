package com.meinil.metms.server.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Meinil
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@TableName("tb_user")
public class User {
    @TableId(type = IdType.ASSIGN_ID)
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

    /**
     * 用户描述
     */
    private String description;

    public User(String id, String username, String account, Integer power) {
        this.id = id;
        this.username = username;
        this.account = account;
        this.power = power;
    }
}
