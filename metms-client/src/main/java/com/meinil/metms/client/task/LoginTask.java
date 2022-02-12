package com.meinil.metms.client.task;

import com.meinil.metms.client.animation.Loading;
import com.meinil.metms.client.animation.PromptBox;
import com.meinil.metms.client.model.User;
import com.meinil.metms.client.request.Request;
import com.meinil.metms.client.utils.Config;
import com.meinil.metms.client.utils.Result;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class LoginTask extends Task<User> {
    private User user;
    private Stage primaryStage;
    public LoginTask(User user, Stage primaryStage) {
        this.user = user;
        this.primaryStage = primaryStage;
    }
    @Override
    protected User call() throws Exception {
        try{
            Loading.show();
            Result result = Request.post("/login", user);
            if (result.getCode() == 200) {
                PromptBox.show("登录成功", PromptBox.SUCCESS);
                User user = result.getValue("user", User.class);
                String token = result.getValue("token", String.class);
                Config.put("user", user);
                Config.put("token", token);
                return user;
            } else {
                PromptBox.show(result.getMessage(), PromptBox.ERROR);
            }
        } finally {
            Loading.hide();
        }
        return null;
    }

    @Override
    protected void updateValue(User value) {
        super.updateValue(value);
        if (value != null) {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/view/fxml/Main.fxml"));
                Scene scene = new Scene(parent);
                scene.setFill(Paint.valueOf("#FFF0"));
                primaryStage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
