package com.meinil.metms.client.component;

import com.alibaba.fastjson.JSONArray;
import com.meinil.metms.client.model.User;
import com.meinil.metms.client.model.vo.TeacherVo;
import com.meinil.metms.client.request.Request;
import com.meinil.metms.client.utils.Config;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.List;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class UpdateController {
    @FXML
    private ChoiceBox<TeacherVo> teachers;

    @FXML
    public void initialize() {
        this.teachers.setConverter(new StringConverter<TeacherVo>() {
            @Override
            public String toString(TeacherVo object) {
                if (object == null) {
                    return "";
                }
                return object.getUsername();
            }

            @Override
            public TeacherVo fromString(String string) {
                return new TeacherVo();
            }
        });
        Request.get("/student/teacherList", r -> {
            List<TeacherVo> teachers = ((JSONArray)r.get("teachers")).toJavaList(TeacherVo.class);
            this.teachers.setItems(FXCollections.observableList(teachers));
        });
    }

    @FXML
    public void onSubmitAction(MouseEvent event) {
        User user = Config.get("user", User.class);
        Request.get("/student/" + teachers.getValue().getId() + "/" + user.getId(),r -> {
            Stage stage = (Stage)teachers.getScene().getWindow();
            Platform.runLater(() -> {
                stage.close();
            });
        });
    }
}
