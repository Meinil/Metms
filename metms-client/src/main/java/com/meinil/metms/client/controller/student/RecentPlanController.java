package com.meinil.metms.client.controller.student;

import com.alibaba.fastjson.JSONArray;
import com.meinil.metms.client.model.Plan;
import com.meinil.metms.client.model.User;
import com.meinil.metms.client.request.Request;
import com.meinil.metms.client.utils.Config;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;

import static com.meinil.metms.client.utils.Config.DATE_TIME_FORMATTER;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class RecentPlanController {
    @FXML
    private TableView<Plan> tcPlan;
    @FXML
    private TableColumn<Plan, String> tcEndTime;
    @FXML
    private TableColumn<Plan, String> tcName;
    @FXML
    private TableColumn<Plan, String> tcPosition;
    @FXML
    private TableColumn<Plan, String> tcStartTime;
    @FXML
    private TableColumn<Plan, String> tcSubmit;

    private ObservableList<Plan> plans;

    @FXML
    public void initialize() {
        generateTable();
        User user = Config.get("user", User.class);
        Request.get("/student/plan/" + user.getAccount(), res -> {
            List<Plan> plans = ((JSONArray)res.get("plans")).toJavaList(Plan.class);
            setData(plans);
        });
    }

    private void setData(List<Plan> plans) {
        Platform.runLater(() -> {
            this.plans = FXCollections.observableArrayList(plans);
            tcPlan.setItems(this.plans);
        });
    }
    /**
     * 设置表格数据格式
     */
    private void generateTable() {
        tcName.setCellValueFactory(new Callback<>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Plan, String> param) {
                return new SimpleStringProperty(param.getValue().getName());
            }
        });
        tcPosition.setCellValueFactory(new Callback<>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Plan, String> param) {
                return new SimpleStringProperty(param.getValue().getPosition());
            }
        });
        tcStartTime.setCellValueFactory(new Callback<>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Plan, String> param) {
                return new SimpleStringProperty(param.getValue().getStartTime().format(DATE_TIME_FORMATTER));
            }
        });
        tcEndTime.setCellValueFactory(new Callback<>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Plan, String> param) {
                return new SimpleStringProperty(param.getValue().getEndTime().format(DATE_TIME_FORMATTER));
            }
        });
        tcSubmit.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Plan, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Plan, String> param) {
                return new SimpleStringProperty(param.getValue().getId());
            }
        });
        tcSubmit.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Plan, String> call(TableColumn<Plan, String> param) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            this.setGraphic(null);
                        } else {
                            Button button = new Button("提交");
                            button.setOnAction(new EventHandler<>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    try {
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/student/SubmitAlert.fxml"));
                                        Parent parent = loader.load();
                                        SubmitAlertController controller = loader.getController();
                                        controller.setId(item);
                                        Stage stage = new Stage();
                                        stage.initStyle(StageStyle.TRANSPARENT);
                                        Scene scene = new Scene(parent);
                                        scene.setFill(Paint.valueOf("#FFF0"));
                                        stage.setScene(scene);
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
    }
}