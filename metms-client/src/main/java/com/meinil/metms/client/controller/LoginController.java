package com.meinil.metms.client.controller;

import com.meinil.metms.client.animation.Loading;
import com.meinil.metms.client.animation.PromptBox;
import com.meinil.metms.client.model.User;
import com.meinil.metms.client.task.LoginTask;
import com.meinil.metms.client.utils.TaskUtils;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


/**
 * @Author Meinil
 * @Version 1.0
 */
public class LoginController{
    @FXML
    private HBox promptBox;
    @FXML
    private TextField tfAccount;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private ImageView loading;

    private User user;

    @FXML
    public void initialize() {
        initTextField();
        PromptBox.setPromptBox(promptBox);      // 初始化提示框
        Loading.setLoading(loading);            // 初始化加载图标
    }

    public void initTextField() {
        user = new User();
        user.accountProperty().bind(tfAccount.textProperty());
        user.passwordProperty().bind(tfPassword.textProperty());
    }

    @FXML
    public void onLoginAction(MouseEvent event)  {
        Stage primaryStage = (Stage)tfAccount.getScene().getWindow();
        TaskUtils.execute(new LoginTask(user, primaryStage));
    }
}
