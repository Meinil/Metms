package com.meinil.metms.client.controller.teacher;

import com.alibaba.fastjson.JSONObject;
import com.meinil.metms.client.animation.Loading;
import com.meinil.metms.client.animation.PromptBox;
import com.meinil.metms.client.model.Plan;
import com.meinil.metms.client.model.User;
import com.meinil.metms.client.request.Request;
import com.meinil.metms.client.utils.Config;
import com.meinil.metms.client.utils.UUIDUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class ReleasePlanController {
    @FXML
    private TextArea content;
    @FXML
    private DatePicker startTime;
    @FXML
    private DatePicker endTime;
    @FXML
    private TextField name;
    @FXML
    private TextField position;
    @FXML
    private Button submitBtn;

    private Plan plan;
    @FXML
    public void initialize() {
        plan = new Plan();
        plan.nameProperty().bind(name.textProperty());
        plan.positionProperty().bind(position.textProperty());
        plan.contentProperty().bind(content.textProperty());
        plan.startTimeProperty().bind(startTime.valueProperty());
        plan.endTimeProperty().bind(endTime.valueProperty());
        plan.setTeacherId(((User)Config.get("user")).getId());
    }

    @FXML
    public void onSubmitAction(MouseEvent event) {
        if (content.getText().length() == 0) {
            PromptBox.show("content内容不能为空", PromptBox.ERROR);
        } else if (name.getText().length() == 0) {
            PromptBox.show("名称不能为空", PromptBox.ERROR);
        } else {
            plan.setId(UUIDUtils.generateUUID());
            submitBtn.setDisable(true);
            Request.post(
                    "/teacher/plan",
                    plan,
                    res -> {
                        submitBtn.setDisable(false);
                    }
            );
        }
    }
}
