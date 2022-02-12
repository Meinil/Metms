package com.meinil.metms.client.task;

import com.meinil.metms.client.animation.Loading;
import com.meinil.metms.client.animation.PromptBox;
import com.meinil.metms.client.controller.admin.PersonnelManagementController;
import com.meinil.metms.client.model.User;
import com.meinil.metms.client.request.Request;
import com.meinil.metms.client.utils.Result;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import javafx.scene.layout.HBox;

import java.util.List;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class PersonnelManagementTask extends Task<Result> {

    private static SimpleIntegerProperty page;
    private HBox pagesPane;
    private PersonnelManagementController controller;

    public PersonnelManagementTask(HBox pagesPane, PersonnelManagementController controller) {
        this.pagesPane = pagesPane;
        this.controller = controller;
    }

    @Override
    protected Result call() throws Exception {
        try{
            Loading.show();
            Result result = Request.get("/admin/page/" + page.get());
            if (result.getCode() == 200) {
                return result;
            }
        } finally {
            Loading.hide();
        }
        return null;
    }

    @Override
    protected void updateValue(Result value) {
        super.updateValue(value);
        if (value != null) {
            if (pagesPane.getChildren().size() == 0) {
                controller.setToggleButton(value);
            }
            List<User> users = value.getListValue("users", User.class);
            controller.setTableData(users);
        } else {
            PromptBox.show(value.getMessage(), PromptBox.ERROR);
        }
    }

    public static void setPage(SimpleIntegerProperty page) {
        PersonnelManagementTask.page = page;
    }
}
