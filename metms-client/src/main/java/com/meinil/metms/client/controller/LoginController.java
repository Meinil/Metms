package com.meinil.metms.client.controller;

import com.meinil.jfxrouter.Router;
import com.meinil.jfxrouter.annotation.FXInit;
import com.meinil.jfxrouter.annotation.FXRouter;
import com.meinil.jfxrouter.annotation.FXView;
import com.meinil.metms.client.component.Loading;
import com.meinil.metms.client.component.PromptBox;
import com.meinil.metms.client.config.CacheConfig;
import com.meinil.metms.client.model.Path;
import com.meinil.metms.client.model.Result;
import com.meinil.metms.client.model.User;
import com.meinil.metms.client.request.Request;
import com.meinil.metms.client.view.LoginView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class LoginController {
    @FXRouter
    private Router router;
    @FXView
    private LoginView loginView;
    private final static Pattern CHAR_PATTERN = Pattern.compile("^[0-9a-zA-Z]*$");

    public LoginController() {
    }

    @FXInit
    public void bindEvent() {
        /**
         * 限制用户只能输入数字
         */
        loginView.getAccount().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Matcher matcher = CHAR_PATTERN.matcher(newValue);
                if (matcher.find()) {
                    loginView.getAccount().setText(newValue);
                } else {
                    loginView.getAccount().setText(oldValue);
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
            String account = loginView.getAccount().getText();
            String password = loginView.getPassword().getText();
            if(account.length() != 0 && password.length() != 0) {
                User user = new User();
                user.setAccount(account);
                user.setPassword(password);
                // 登录
                new Thread(new UserTask(user)).start();
            }
        });
    }

    private class UserTask extends Task<Void> {
        private User user;
        public UserTask(User user) {
            this.user = user;
        }
        @Override
        protected Void call() throws Exception {
            try{
                Loading.setShow();
                Result result = Request.post(Path.LOGIN, user);
                System.out.println(result);
                if (result.getCode() == 200) {
                    PromptBox.setShow(result.getMessage(), PromptBox.SUCCESS);
                    CacheConfig.save("token", result.get("token", String.class));
                    User user = result.get("user", User.class);
                    CacheConfig.save(user);
                    router.push("/home", user);
                } else {
                    PromptBox.setShow(result.getMessage(), PromptBox.ERROR);
                }
            } finally {
                Loading.setHide();
            }
            return null;
        }
    }
}
