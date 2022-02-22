package com.meinil.metms.client.controller.teacher;

import com.alibaba.fastjson.JSONArray;
import com.meinil.metms.client.model.User;
import com.meinil.metms.client.model.dto.ExperienceDto;
import com.meinil.metms.client.request.Request;
import com.meinil.metms.client.utils.Config;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.util.List;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class ApprovalPlanController {
    @FXML
    private TableView<ExperienceDto> tcPlan;
    @FXML
    private TableColumn<ExperienceDto, String> tcApprove;
    @FXML
    private TableColumn<ExperienceDto, String> tcCreateTime;
    @FXML
    private TableColumn<ExperienceDto, String> tcTitle;
    @FXML
    private TableColumn<ExperienceDto, String> tcStudent;
    @FXML
    public void initialize() {
        initTable();
        User user = Config.get("user", User.class);
        Request.get("/teacher/plan/approval/pending/" + user.getAccount(), res -> {
            List<ExperienceDto> experience = ((JSONArray) res.get("experience")).toJavaList(ExperienceDto.class);
            tcPlan.setItems(FXCollections.observableList(experience));
        });
    }

    private void initTable() {
        tcTitle.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExperienceDto, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExperienceDto, String> param) {
                return new SimpleStringProperty(param.getValue().getTitle());
            }
        });
        tcStudent.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExperienceDto, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExperienceDto, String> param) {
                return new SimpleStringProperty(param.getValue().getStuName());
            }
        });
        tcCreateTime.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExperienceDto, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExperienceDto, String> param) {
                return new SimpleStringProperty(param.getValue().getCreatedTime().format(Config.DATE_TIME_FORMATTER));
            }
        });
        tcApprove.setCellFactory(new Callback<TableColumn<ExperienceDto, String>, TableCell<ExperienceDto, String>>() {
            @Override
            public TableCell<ExperienceDto, String> call(TableColumn<ExperienceDto, String> param) {
                return new TableCell<>(){
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            this.setGraphic(null);
                        } else {
                            this.setGraphic(new Button("批改"));
                        }
                    }
                };
            }
        });
        tcApprove.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExperienceDto, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExperienceDto, String> param) {
                return new SimpleStringProperty(param.getValue().getCorrect());
            }
        });
    }
}
