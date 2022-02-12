package com.meinil.metms.client.controller;

import com.meinil.metms.client.animation.Loading;
import com.meinil.metms.client.animation.PromptBox;
import com.meinil.metms.client.component.MXToggle;
import com.meinil.metms.client.model.User;
import com.meinil.metms.client.utils.Config;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class MainController {
    @FXML
    private BorderPane topMenuPane;
    @FXML
    private BorderPane mainPane;
    @FXML
    private AnchorPane contentPane;
    @FXML
    private ImageView loading;
    @FXML
    private HBox promptBox;

    private User user;

    @FXML
    public void initialize() {
        user = Config.get("user", User.class);
        Loading.setLoading(loading);
        PromptBox.setPromptBox(promptBox);
        initTopMenu();
    }

    private void initTopMenu() {
        MXToggle mxToggle = (MXToggle)topMenuPane.lookup("#top-menu-toggle-pane");
        mxToggle.setShow(contentPane);
        switch (user.getPower()) {
            case 0:
                mxToggle.setPath("/view/fxml/admin/");
                ToggleButton btn1 = new ToggleButton("人员管理");
                btn1.setAccessibleText("PersonnelManagement");
                ToggleButton btn2 = new ToggleButton("导入数据");
                btn2.setAccessibleText("ImportData");
                mxToggle.add(btn1, btn2);
                break;
            case 2:
                mxToggle.setPath("/view/fxml/teacher/");
                ToggleButton btn3 = new ToggleButton("审批计划");
                btn3.setAccessibleText("ApprovalPlan");
                ToggleButton btn4 = new ToggleButton("发布计划");
                btn4.setAccessibleText("ReleasePlan");
                ToggleButton btn5 = new ToggleButton("我的学生");
                btn5.setAccessibleText("MyStudent");
                mxToggle.add(btn3, btn4, btn5);
                break;
            default:
                mxToggle.setPath("/view/fxml/student/");
                ToggleButton btn6 = new ToggleButton("最近计划");
                btn6.setAccessibleText("RecentPlan");
                ToggleButton btn7 = new ToggleButton("我的导师");
                btn7.setAccessibleText("MyMentor");
                mxToggle.add(btn6, btn7);
        }
    }
}
