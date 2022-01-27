package com.meinil.metms.client;

import com.alibaba.fastjson.parser.ParserConfig;
import com.meinil.jfxrouter.Route;
import com.meinil.jfxrouter.Router;
import com.meinil.jfxrouter.RouterView;
import com.meinil.metms.client.component.Loading;
import com.meinil.metms.client.component.PromptBox;
import com.meinil.metms.client.config.CacheConfig;
import com.meinil.metms.client.model.User;
import com.meinil.metms.client.view.HomeView;
import com.meinil.metms.client.view.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MetmsClientApplication extends Application {
    public static void main(String[] args) {
        launch();
    }
    private static Stage primaryStage;
    Router router = new Router(
            new Route(LoginView.class, null, false),
            new Route(HomeView.class)
    );

    @Override
    public void init() throws Exception {
        ParserConfig.getGlobalInstance().addAccept("com.meinil.metms.commons");
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        AnchorPane root = new AnchorPane();

        RouterView routerView = new RouterView();
        routerView.prefWidthProperty().bind(root.widthProperty());
        routerView.prefHeightProperty().bind(root.heightProperty());
        router.mount(routerView);

        root.getChildren().addAll(routerView, Loading.getLoading(root), PromptBox.getPromptBox(root));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/static/css/global.css").toExternalForm());
        stage.setWidth(600);
        stage.setHeight(400);
        stage.getIcons().add(new Image(getClass().getResource("/static/image/favicon.png").toExternalForm()));
        stage.setScene(scene);
        stage.show();
        if (CacheConfig.get("token").length() != 0) {
            User user = new User();
            user.setUsername(CacheConfig.get("username"));
            user.setAccount(CacheConfig.get("account"));
            user.setPower(Integer.valueOf(CacheConfig.get("power")));
            router.push("/home", user);
        } else {
            router.push("/login");
        }
    }

    public static void setStageSize(double width, double height) {
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
    }
}