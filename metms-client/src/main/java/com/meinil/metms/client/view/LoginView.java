package com.meinil.metms.client.view;

import com.meinil.jfxrouter.RouterView;
import com.meinil.jfxrouter.annotation.View;
import com.meinil.metms.client.controller.LoginController;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class LoginView {
    /**
     * 登录按钮
     */
    private Button loginBtn;
    /**
     * 用户名输入框
     */
    private TextField username;
    /**
     * 密码输入框
     */
    private TextField password;
    /**
     * 登录控制器
     */
    private LoginController controller;
    @View(path = "/login")
    public Node getView(RouterView routerView) {
        AnchorPane pane = new AnchorPane();
        pane.prefWidthProperty().bind(routerView.prefWidthProperty());
        pane.prefHeightProperty().bind(routerView.prefHeightProperty());

        Image image1 = new Image(
                getClass().getResource("/static/image/login_bg.jpeg").toExternalForm(),
                250,
                250,
                true,
                true,
                false
        );
        ImageView loginBackground = new ImageView(image1);
        loginBackground.setPreserveRatio(true);
        loginBackground.fitHeightProperty().bind(routerView.prefHeightProperty());
        VBox left = new VBox();
        left.getChildren().add(loginBackground);

        VBox right = new VBox(10);
        right.setAlignment(Pos.CENTER);
        right.prefWidthProperty().bind(routerView.prefWidthProperty().subtract(left.widthProperty()));
        right.prefHeightProperty().bind(routerView.prefHeightProperty());
        Image image2 = new Image(
               getClass().getResource("/static/image/logo.png").toExternalForm(),
               200,
               200,
               true,
               true,
               false
        );
        ImageView logo = new ImageView(image2);
        username = new TextField();
        username.setPromptText("请输入用户名");
        username.setMaxWidth(200);
        username.setFocusTraversable(false);
        password = new PasswordField();
        password.setPromptText("请输入密码");
        password.setMaxWidth(200);
        password.setFocusTraversable(false);
        loginBtn = new Button("登录");
        loginBtn.setPrefWidth(200);
        right.getChildren().addAll(logo, username, password, loginBtn);

        pane.getChildren().addAll(left, right);
        pane.setMinSize(600, 400);
        AnchorPane.setRightAnchor(right, 0.0);

        controller = new LoginController(this);

        return pane;
   }

    public Button getLoginBtn() {
        return loginBtn;
    }

    public TextField getUsername() {
        return username;
    }

    public TextField getPassword() {
        return password;
    }
}
