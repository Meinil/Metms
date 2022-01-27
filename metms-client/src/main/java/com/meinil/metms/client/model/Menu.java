package com.meinil.metms.client.model;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class Menu {
    /**
     * 菜单名字
     */
    private String name;
    /**
     * 路由
     */
    private String path;

    public Menu(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}
