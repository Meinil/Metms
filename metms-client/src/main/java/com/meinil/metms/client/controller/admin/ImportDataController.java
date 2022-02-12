package com.meinil.metms.client.controller.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class ImportDataController {
    @FXML
    private VBox uploadContent;
    @FXML
    private Label fileName;
    @FXML
    private Button submitBtn;

    @FXML
    public void initialize() {
        submitBtn.disableProperty().bind(fileName.textProperty().isEqualTo(""));
    }

    @FXML
    public void onChoiceAction(MouseEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("选择学生数据");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("excel类型", "*.xlsx"));
        File file = chooser.showOpenDialog(uploadContent.getScene().getWindow());
        if (file != null) {
            fileName.setText(file.getName());
            readData(file);
        }
    }

    private void readData(File file) {

    }
}
