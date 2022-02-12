package com.meinil.metms.client.controller.admin;

import com.meinil.metms.client.model.User;
import com.meinil.metms.client.task.PersonnelManagementTask;
import com.meinil.metms.client.utils.Result;
import com.meinil.metms.client.utils.TaskUtils;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class PersonnelManagementController {
    @FXML
    private TableColumn<User, String> tcAccount;
    @FXML
    private TableColumn<User, String> tcId;
    @FXML
    private TableColumn<User, String> tcName;
    @FXML
    private TableColumn<User, String> tcPower;
    @FXML
    private TableView<User> tvUser;
    @FXML
    private HBox pagesPane;
    private SimpleIntegerProperty page = new SimpleIntegerProperty(1);
    private Integer total;
    private List<ToggleButton> btns;

    private ObservableList<User> data;

    @FXML
    public void initialize() {
        PersonnelManagementTask.setPage(page);
        page.addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                TaskUtils.execute(new PersonnelManagementTask(pagesPane, PersonnelManagementController.this));
            }
        });
        TaskUtils.execute(new PersonnelManagementTask(pagesPane, this));
    }

    @FXML
    public void onNextAction(MouseEvent event) {
        if (page.get() == total) {
            page.set(1);
        } else {
            page.add(1);
        }
        btns.get(page.get() - 1).setSelected(true);
    }

    @FXML
    public void onPrevAction(MouseEvent event) {
        if (page.get() == 1) {
            page.set(total);
        } else {
            page.subtract(1);
        }
        btns.get(page.get() - 1).setSelected(true);
    }

    /**
     * 设置表格数据
     * @param users 用户数据
     */
    public void setTableData(List<User> users) {
        data = FXCollections.observableList(users);
        tvUser.setItems(data);

        tcId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> param) {
                return new SimpleStringProperty(param.getValue().getId());
            }
        });

        tcName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().usernameProperty();
            }
        });

        tcAccount.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().accountProperty();
            }
        });

        tcPower.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> param) {
                String value = switch (param.getValue().getPower()) {
                    case 0 -> "管理员";
                    case 2 -> "教师";
                    default -> "学生";
                };
                return param.getValue().powerProperty().asString(value);
            }
        });
    }

    /**
     * 设置翻页按钮
     * @param value 请求返回值中含有按钮的总数量
     */
    public void setToggleButton(Result value) {
        total = value.getValue("total", Integer.class);
        total = total < 10 ? 1 : (int)Math.ceil(total / 10.0);
        btns = new ArrayList<>(total);
        ToggleGroup group = new ToggleGroup();
        for (int i = 1; i <= total; i++) {
            ToggleButton btn = new ToggleButton(Integer.toString(i));
            if (i == 1) {
                btn.setSelected(true);
            }
            btn.setToggleGroup(group);
            btn.setOnMouseClicked(event -> {
                if (btn.isSelected()) {
                    page.set(Integer.valueOf(btn.getText()));
                }
            });
            btns.add(btn);
            pagesPane.getChildren().add(btn);
        }
    }
}

