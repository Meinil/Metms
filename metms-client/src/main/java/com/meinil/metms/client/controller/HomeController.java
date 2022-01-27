package com.meinil.metms.client.controller;

import com.meinil.jfxrouter.Router;
import com.meinil.jfxrouter.annotation.FXInit;
import com.meinil.jfxrouter.annotation.FXView;
import com.meinil.metms.client.model.Menu;
import com.meinil.metms.client.view.HomeView;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class HomeController {
    @FXView
    private HomeView homeView;
    private Router router;

    /**
     * 隐藏动画
     */
    private TranslateTransition hide;
    /**
     * 显示动画
     */
    private TranslateTransition show;
    private boolean isShow = true;

    @FXInit
    public void init() {
        // 绑定动画
        AnchorPane left = homeView.getLeft();
        leftAnimation(left);

        ImageView avatar = homeView.getAvatar();
        avatar.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (isShow) {
                hide.play();
            } else{
                show.play();
            }
        });

        ListView<Menu> listMenu = homeView.getListMenu();
        router = homeView.getHomeRouter();

        listMenu.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Menu>() {
            @Override
            public void changed(ObservableValue<? extends Menu> observable, Menu oldValue, Menu newValue) {
                router.push(newValue.getPath());
                hide.play();
            }
        });
    }

    public void leftAnimation(AnchorPane left) {
        hide = new TranslateTransition();
        hide.setFromX(0);
        hide.setToX(-left.getPrefWidth() + 8);
        hide.setNode(left);
        hide.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                isShow = false;
            }
        });

        show = new TranslateTransition();
        show.setFromX(-left.getPrefWidth() + 8);
        show.setToX(0);
        show.setNode(left);
        show.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                isShow = true;
            }
        });
    }
}
