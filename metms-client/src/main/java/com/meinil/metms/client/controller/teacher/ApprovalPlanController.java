package com.meinil.metms.client.controller.teacher;

import com.alibaba.fastjson.JSONArray;
import com.meinil.metms.client.component.ApproveController;
import com.meinil.metms.client.model.User;
import com.meinil.metms.client.model.dto.ExperienceDto;
import com.meinil.metms.client.request.Request;
import com.meinil.metms.client.utils.Config;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class ApprovalPlanController {
    @FXML
    private TableView<ExperienceDto> tcPlan;
    @FXML
    private TableColumn<ExperienceDto, ExperienceDto> tcApprove;
    @FXML
    private TableColumn<ExperienceDto, String> tcCreateTime;
    @FXML
    private TableColumn<ExperienceDto, String> tcTitle;
    @FXML
    private TableColumn<ExperienceDto, String> tcStudent;
    @FXML
    private TableColumn<ExperienceDto, String> tcLevel;
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
        tcLevel.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExperienceDto, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExperienceDto, String> param) {
                return new SimpleStringProperty(param.getValue().getLevel());
            }
        });
        tcApprove.setCellFactory(new Callback<TableColumn<ExperienceDto, ExperienceDto>, TableCell<ExperienceDto, ExperienceDto>>() {
            @Override
            public TableCell<ExperienceDto, ExperienceDto> call(TableColumn<ExperienceDto, ExperienceDto> param) {
                return new TableCell<>(){
                    @Override
                    protected void updateItem(ExperienceDto item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            this.setGraphic(null);
                        } else {
                            Button button = new Button("批改");
                            button.setOnAction(new EventHandler<>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    Stage stage = new Stage();
                                    try {
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/components/fxml/Approve.fxml"));
                                        Parent parent = loader.load();
                                        ApproveController controller = loader.getController();
                                        controller.setData(item);
                                        stage.setScene(new Scene(parent));
                                        stage.show();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            this.setGraphic(button);
                        }
                    }
                };
            }
        });
        tcApprove.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExperienceDto, ExperienceDto>, ObservableValue<ExperienceDto>>() {
            @Override
            public ObservableValue<ExperienceDto> call(TableColumn.CellDataFeatures<ExperienceDto, ExperienceDto> param) {
                return new SimpleObjectProperty<>(param.getValue());
            }
        });
    }
}
