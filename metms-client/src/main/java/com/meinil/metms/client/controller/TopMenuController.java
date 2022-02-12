package com.meinil.metms.client.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class TopMenuController {
    @FXML
    private ToggleButton minBtn;

    private double offsetX, offsetY;

    @FXML
    public void onCloseAction(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    public void onMinAction(MouseEvent event) {
        Stage primaryStage = (Stage)minBtn.getScene().getWindow();
        minBtn.setSelected(false);
        primaryStage.setIconified(true);
    }

    @FXML
    public void onSetOffsetAction(MouseEvent event) {
        offsetX = event.getSceneX();
        offsetY = event.getSceneY();
    }

    @FXML
    public void onSetScreenAction(MouseEvent event) {
        Stage primaryStage = (Stage)minBtn.getScene().getWindow();
        primaryStage.setX(event.getScreenX() - offsetX);
        primaryStage.setY(event.getScreenY() - offsetY);
    }
}
