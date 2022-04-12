package com.meinil.metms.client.component;

import com.meinil.metms.client.animation.PromptBox;
import com.meinil.metms.client.model.dto.ExperienceDto;
import com.meinil.metms.client.request.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class ApproveController {
    @FXML
    private TextArea correct;
    @FXML
    private TextArea content;
    @FXML
    private ChoiceBox<String> level;

    private ExperienceDto experience;

    @FXML
    public void initialize() {
        level.getItems().addAll("A", "B", "C", "D");
        level.setValue("选择等级");
    }

    @FXML
    public void onSubmitAction(ActionEvent event) {
        experience.setContent(content.getText());
        experience.setCorrect(correct.getText());
        experience.setLevel(level.getValue());
        Request.post("/teacher/approval", experience);
        PromptBox.show("修改成功", PromptBox.SUCCESS);
        ((Stage)content.getScene().getWindow()).close();
    }

    public void setData(ExperienceDto experience) {
        this.experience = experience;
        correct.setText(experience.getCorrect());
        content.setText(experience.getContent());
        if (!Objects.isNull(experience.getLevel()) || !experience.getLevel().equals("D")) {
            level.getSelectionModel().select(experience.getLevel());
        }
    }
}
