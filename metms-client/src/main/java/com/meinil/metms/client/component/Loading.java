package com.meinil.metms.client.component;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class Loading  {
    private static HBox loading;

    private Loading() {}

    public static Node getLoading(AnchorPane pane) {
        if (loading != null) {
            return loading;
        }
        loading = new HBox();
        loading.prefWidthProperty().bind(pane.widthProperty());
        loading.prefHeightProperty().bind(pane.heightProperty());
        loading.setAlignment(Pos.CENTER);
        Image image = new Image(Loading.class.getResource("/components/loading.gif").toExternalForm());
        ImageView iv = new ImageView(image);
        loading.getChildren().add(iv);
        setHide();
        return loading;
    }

    /**
     * 设置loading显示
     */
    public static void setShow() {
        if (loading == null) {
            return;
        }
        loading.setVisible(true);
    }

    /**
     * 设置loading隐藏
     */
    public static void setHide() {
        if (loading == null) {
            return;
        }
        loading.setVisible(false);
    }
}
