package com.meinil.metms.client.view;

import com.meinil.jfxrouter.RouterView;
import com.meinil.jfxrouter.annotation.View;
import com.meinil.metms.client.component.Pages;
import com.meinil.metms.client.controller.AdminController;
import com.meinil.metms.client.model.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class AdminView {
    private TableView<User> tableView;
    private Pages pages;

    @View(path = "/admin/user", controller = AdminController.class)
    public Node getView(RouterView routerView) {
        VBox box = new VBox(30);
        box.setStyle("-fx-padding: 20 100 20 100");

        box.prefWidthProperty().bind(routerView.prefWidthProperty());
        box.prefHeightProperty().bind(routerView.prefHeightProperty());
        box.setAlignment(Pos.CENTER);

        HBox menu = new HBox(20);
        menu.getChildren().addAll(new Button("导入"), new Button("删除用户"), new Button("修改用户"));

        TableView<User> tableView = getTableView();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        pages = new Pages();
        HBox page = new Pages().getPages();

        box.getChildren().addAll(menu, tableView, page);
        return box;
    }
    /**
     * 获取
     */

    /**
     * 获取分页对象
     */
    public Pages getPages() {
        return pages;
    }

    /**
     * 获取tableView
     * @return tableView
     */
    public TableView<User> getTableView() {
        if (tableView != null) {
            return tableView;
        }
        TableView<User> tableView = new TableView<>();
        TableColumn<User, String> tcId = new TableColumn<>("id");
        tcId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> param) {
                return new SimpleStringProperty(param.getValue().getId());
            }
        });

        TableColumn<User, String> tcName = new TableColumn<>("用户名");
        tcName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> param) {
                return new SimpleStringProperty(param.getValue().getUsername());
            }
        });

        TableColumn<User, String> tcAccount = new TableColumn<>("账号");
        tcAccount.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> param) {
                return new SimpleStringProperty(param.getValue().getAccount());
            }
        });

        TableColumn<User, String> tcPower = new TableColumn<>("身份");
        tcPower.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> param) {
                return switch (param.getValue().getPower()) {
                    case 0 -> new SimpleStringProperty("管理员");
                    case 2 -> new SimpleStringProperty("教师");
                    case 3 -> new SimpleStringProperty("学生");
                    default -> new SimpleStringProperty("非法用户");
                };
            }
        });

        tableView.getColumns().addAll(tcId, tcName, tcAccount, tcPower);

        return tableView;
    }
}
