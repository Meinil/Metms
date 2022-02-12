package com.meinil.metms.client;

import com.meinil.metms.client.model.User;
import com.meinil.metms.client.utils.Config;
import com.meinil.metms.client.utils.TaskUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MetmsClientApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/fxml/Login.fxml"));

//        User cacheUser = Config.get("user", User.class);
//        Parent parent;
//        if (cacheUser != null) {
//            parent = FXMLLoader.load(getClass().getResource("/view/fxml/Main.fxml"));
//        } else {
//            parent = FXMLLoader.load(getClass().getResource("/view/fxml/Login.fxml"));
//        }
        Scene scene = new Scene(parent);
        scene.setFill(Paint.valueOf("#FFFFFF00"));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        TaskUtils.shutDown();
    }
}