package com.meinil.metms.client.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class User {
    private String id;
    private SimpleStringProperty account = new SimpleStringProperty();
    private SimpleStringProperty password = new SimpleStringProperty();
    private SimpleStringProperty username = new SimpleStringProperty();
    private SimpleIntegerProperty power = new SimpleIntegerProperty();
    /**
     * 用户描述
     */
    private String description;

    public void setId(String id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account.set(account);
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public void setPower(int power) {
        this.power.set(power);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getAccount() {
        return account.get();
    }

    public SimpleStringProperty accountProperty() {
        return account;
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public int getPower() {
        return power.get();
    }

    public SimpleIntegerProperty powerProperty() {
        return power;
    }

    public String getDescription() {
        return description;
    }
}
