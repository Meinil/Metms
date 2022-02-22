package com.meinil.metms.client.controller.teacher;

import com.alibaba.fastjson.JSONArray;
import com.meinil.metms.client.model.User;
import com.meinil.metms.client.request.Request;
import com.meinil.metms.client.utils.Config;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.util.List;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class MyStudentController {
    @FXML
    private TableColumn<User, String> tcAccount;
    @FXML
    private TableColumn<User, String> tcName;

    @FXML
    private TableView<User> tcStudent;

    @FXML
    public void initialize() {
        initTable();
        User user = Config.get("user", User.class);
        Request.get("/teacher/student/" + user.getAccount(), res -> {
            List<User> users = ((JSONArray) res.get("students")).toJavaList(User.class);
            tcStudent.setItems(FXCollections.observableList(users));
        });
    }

    private void initTable() {
        tcName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> param) {
                return new SimpleStringProperty(param.getValue().getUsername());
            }
        });

        tcAccount.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> param) {
                return new SimpleStringProperty(param.getValue().getAccount());
            }
        });
    }
}
