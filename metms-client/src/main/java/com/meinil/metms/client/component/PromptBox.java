package com.meinil.metms.client.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class PromptBox {
    private final static Image error = new Image(PromptBox.class.getResource("/components/error.png").toExternalForm());
    private final static Image success = new Image(PromptBox.class.getResource("/components/error.png").toExternalForm());

    private AnchorPane root;
    public PromptBox(AnchorPane root) {
        this.root = root;
    }

    public Node getErrorView(String message) {
        return getPromptBox(message, error);
    }

    public Node getSuccessView(String message) {
        return getPromptBox(message, success);
    }

    private Node getPromptBox(String message, Image image) {
        HBox box = new HBox(30);
        box.setStyle("-fx-background-color: WHITE");
        box.setPadding(new Insets(10, 20, 10, 20));
        box.setAlignment(Pos.CENTER);

        ImageView iv = new ImageView(error);
        iv.setPreserveRatio(true);
        iv.setFitWidth(32);

        Text text = new Text(message);
        box.getChildren().addAll(iv, text, new Button("x"));
        DropShadow ds = new DropShadow(5, new Color(0.1, 0.1, 0.1, 0.2));
        box.setEffect(ds);
        box.setBorder(new Border(new BorderStroke(
                Paint.valueOf("#00000022"),
                BorderStrokeStyle.SOLID,
                null,
                new BorderWidths(2))
        ));

        AnchorPane.setTopAnchor(box, 20.0);
        AnchorPane.setRightAnchor(box, 20.0);
        return box;
    }

    public void show() {

    }

    public void hide() {

    }
}
