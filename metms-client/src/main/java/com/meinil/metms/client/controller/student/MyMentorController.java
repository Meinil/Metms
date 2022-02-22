package com.meinil.metms.client.controller.student;

import com.alibaba.fastjson.JSONObject;
import com.meinil.metms.client.model.User;
import com.meinil.metms.client.request.Request;
import com.meinil.metms.client.utils.Config;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class MyMentorController {
    @FXML
    private Text account;
    @FXML
    private Text descriptionText;
    @FXML
    private Text nameText;

    @FXML
    public void initialize() {
        User user = Config.get("user", User.class);
        Request.get("/student/teacher/" + user.getAccount(), res -> {
            User mentor = ((JSONObject)res.get("mentor")).toJavaObject(User.class);
            account.setText(mentor.getAccount());
            nameText.setText(mentor.getUsername());
            descriptionText.setText(mentor.getDescription());
        });
    }
}
