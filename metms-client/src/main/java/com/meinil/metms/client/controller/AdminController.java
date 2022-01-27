package com.meinil.metms.client.controller;

import com.meinil.jfxrouter.Router;
import com.meinil.jfxrouter.annotation.FXInit;
import com.meinil.jfxrouter.annotation.FXRouter;
import com.meinil.jfxrouter.annotation.FXView;
import com.meinil.metms.client.component.Pages;
import com.meinil.metms.client.component.PromptBox;
import com.meinil.metms.client.model.Path;
import com.meinil.metms.client.model.Result;
import com.meinil.metms.client.model.User;
import com.meinil.metms.client.request.Request;
import com.meinil.metms.client.view.AdminView;
import javafx.concurrent.Task;
import javafx.scene.control.TableView;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class AdminController {
    @FXView
    private AdminView adminView;
    @FXRouter
    private Router router;

    private Pages pages;
    private TableView<User> tableView;

    @FXInit
    public void init() {
        pages = adminView.getPages();

        tableView = adminView.getTableView();
        new Thread(new AdminTask(0)).start(); // 初始化
    }

    private class AdminTask extends Task<Result> {
        private final int number;
        public AdminTask(int number) {
            this.number = number;
        }
        @Override
        protected Result call() throws Exception {
            Result result = Request.get(String.format("%s/%d", Path.USER_COUNT, number));
            if (result.getCode() == 200) {
                if (number == 0) {
                    new Thread(new AdminTask(1)).start(); // 获取第一页
                }
                return result;
            }
            if (result.getCode() == 401) {
                PromptBox.setShow(result.getMessage(), PromptBox.ERROR);
                router.push("/login");
            }

            throw new RuntimeException("异常");
        }

        @Override
        protected void updateValue(Result value) {
            super.updateValue(value);
            if (number == 0) {
                pages.setNumber(value.get("total", Integer.class));
                System.out.println(value.get("total", Integer.class));
            } else {

            }
        }
    }
}
