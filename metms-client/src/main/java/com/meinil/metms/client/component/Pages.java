package com.meinil.metms.client.component;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class Pages {
    private HBox box;
    public Pages() {
        box = new HBox(5);
    }
    public HBox getPages() {
        box.setAlignment(Pos.CENTER);
        box.getChildren().add(new Text("空空如也"));
        return box;
    }

    public void setNumber(int number) {
        box.getChildren().clear();
        for (int i = 1; i <= number; i++) {
            Button btn = new Button(String.valueOf(i));
            btn.setStyle("-fx-background-color: #F4F4F5; -fx-text-fill: rgb(96, 98, 102)");
            box.getChildren().add(btn);
        }
        Button pre = new Button("<");
        pre.setStyle("-fx-background-color: #F4F4F5; -fx-text-fill: rgb(96, 98, 102)");
        box.getChildren().add(0, pre);
        Button next = new Button(">");
        next.setStyle("-fx-background-color: #F4F4F5; -fx-text-fill: rgb(96, 98, 102)");
        box.getChildren().addAll(next);
    }
}
