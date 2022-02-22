package com.meinil.metms.client.controller.student;

import com.meinil.metms.client.animation.PromptBox;
import com.meinil.metms.client.model.Experience;
import com.meinil.metms.client.model.User;
import com.meinil.metms.client.request.Request;
import com.meinil.metms.client.utils.Config;
import com.meinil.metms.client.utils.UUIDUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class SubmitAlertController {
    @FXML
    private TextArea contentText;

    /**
     * 当前计划id
     */
    private String id;
    private User user;

    @FXML
    public void initialize() {
        user = Config.get("user", User.class);
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 关闭当前提交窗口
     * @param event 鼠标释放时触发
     */
    @FXML
    public void onCloseAction(MouseEvent event) {
        Stage stage = (Stage)contentText.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onConfirmAction(MouseEvent event) {
        Experience experience = new Experience();
        experience.setId(UUIDUtils.generateUUID());
        experience.setContent(contentText.getText());
        experience.setPlanId(id);
        experience.setStuId(user.getAccount());

        Request.post("/student/experience", experience, res -> {
            Platform.runLater(() -> {
                PromptBox.show("插入成功", PromptBox.SUCCESS);
                Stage stage = (Stage)contentText.getScene().getWindow();
                stage.close();
            });
        });
    }
}
