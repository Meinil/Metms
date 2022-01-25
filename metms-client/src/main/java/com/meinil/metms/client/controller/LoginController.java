package com.meinil.metms.client.controller;

import com.alibaba.fastjson.JSONObject;
import com.meinil.metms.client.request.Request;
import com.meinil.metms.client.view.LoginView;
import com.meinil.metms.commons.Path;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class LoginController {
    private LoginView loginView;
    private final static Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]*$");
    private final static Pattern CHAR_PATTERN = Pattern.compile("^[0-9a-zA-Z]*$");

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        bindEvent();        // 绑定事件
    }

    public void bindEvent() {
        /**
         * 限制用户只能输入数字
         */
        loginView.getUsername().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Matcher matcher = NUMBER_PATTERN.matcher(newValue);
                if (matcher.find()) {
                    loginView.getUsername().setText(newValue);
                } else {
                    loginView.getUsername().setText(oldValue);
                }
            }
        });
        /**
         * 限制用户只能输入[0-9a-zA-Z]
         */
        loginView.getPassword().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Matcher matcher = CHAR_PATTERN.matcher(newValue);
                if (matcher.find()) {
                    loginView.getPassword().setText(newValue);
                } else {
                    loginView.getPassword().setText(oldValue);
                }
            }
        });

        loginView.getLoginBtn().setOnAction(event -> {
            String username = loginView.getUsername().getText();
            String password = loginView.getPassword().getText();
            if(username.length() != 0 && password.length() != 0) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("username", username);
                jsonObject.put("password", password);
                HttpResponse<String> post = Request.post(Path.Login, jsonObject);
            }

        });
    }
}
