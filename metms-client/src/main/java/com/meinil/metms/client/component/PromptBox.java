package com.meinil.metms.client.component;

import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class PromptBox {
    public final static Image ERROR = new Image(PromptBox.class.getResource("/components/error.png").toExternalForm());
    public final static Image SUCCESS = new Image(PromptBox.class.getResource("/components/success.png").toExternalForm());
    private static HBox container;
    private static ImageView iv;
    private static Text text;

    /**
     * 显示隐藏动画
     */
    private static TranslateTransition show;
    private static TranslateTransition hide;

    private PromptBox() {}

    /**
     * 获取提示框对象
     * @param root 容器
     * @return 提示框对象
     */
    public static Node getPromptBox(AnchorPane root) {
        if (container != null) {
            return container;
        }
        container = new HBox();
        container.setAlignment(Pos.CENTER);
        container.setStyle("-fx-background-color: #FFFFFF00");
        container.prefWidthProperty().bind(root.widthProperty());

        iv = new ImageView(ERROR);
        iv.setPreserveRatio(true);
        iv.setFitHeight(32);
        text = new Text();

        HBox box = new HBox(20);
        box.setAlignment(Pos.CENTER);
        box.setStyle("-fx-background-color: SKYBLUE; -fx-padding: 6 20 6 20; -fx-font-size: 18");
        box.getChildren().addAll(iv, text);
        boxAnimation(container);  // 绑定动画
        container.setTranslateY(-50);
        container.getChildren().add(box);
        return container;
    }

    /**
     * 显示提示框
     * @param message 提示信息
     * @param type  图片类型
     */
    public static void setShow(String message, Image type) {
        if (container == null) {
            return;
        }
        iv.setImage(type);
        text.setText(message);
        show.play();
        show.setOnFinished(event -> {
            setHide();
        });
    }

    /**
     * 隐藏提示框
     */
    private static void setHide() {
        if (container == null) {
            return;
        }
        hide.play();
    }

    public static void boxAnimation(HBox box) {
        show = new TranslateTransition(Duration.millis(100));
        show.setFromY(-50);
        show.setToY(0);
        show.setNode(box);

        hide = new TranslateTransition(Duration.millis(100));
        hide.setFromY(0);
        hide.setToY(-50);
        hide.setNode(box);
        hide.setDelay(Duration.seconds(2));
    }
}
