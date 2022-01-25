package com.meinil.metms.client;

import com.meinil.jfxrouter.Route;
import com.meinil.jfxrouter.Router;
import com.meinil.jfxrouter.RouterView;
import com.meinil.metms.client.view.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MetmsClientApplication extends Application {
    Router router = new Router(
            new Route(LoginView.class, null, false)
    );
    @Override
    public void start(Stage stage) throws IOException {
        AnchorPane root = new AnchorPane();

        RouterView routerView = new RouterView();
        routerView.prefWidthProperty().bind(root.widthProperty());
        routerView.prefHeightProperty().bind(root.heightProperty());
        router.mount(routerView);

        root.getChildren().add(routerView);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/static/css/global.css").toExternalForm());
        stage.setWidth(600);
        stage.setHeight(400);
        stage.getIcons().add(new Image(getClass().getResource("/static/image/favicon.png").toExternalForm()));
        stage.setScene(scene);
        stage.show();
        router.push("/login");
    }

    public static void main(String[] args) {
        launch();
    }
}