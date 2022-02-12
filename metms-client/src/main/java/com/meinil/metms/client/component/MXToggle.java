package com.meinil.metms.client.component;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Meinil
 * @Version 1.0
 * MXToggle 会获取ToggleButton中的AccessibleText当做fxml的文件名
 */
public class MXToggle extends HBox {
    private ToggleGroup group;
    /**
     * fxml父路径
     */
    private String path;

    /**
     * 组件展示的地方
     */
    private Pane show;

    private HashMap<String, Parent> parents;

    public MXToggle() {
        super();
        setAlignment(Pos.CENTER);
        getStyleClass().add("mx-toggle");
        getStylesheets().add(getClass().getResource("/components/css/MXToggle.css").toExternalForm());
    }

    public void add(ToggleButton ...btns) {
        if (group == null) {
            group = new ToggleGroup();
            parents = new HashMap<>();
        }
        // 设置第一个为首选项
        btns[0].setSelected(true);
        setContent(btns[0]);
        for (ToggleButton tb : btns) {
            tb.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (tb.isSelected()) {
                        // 切换组件
                        setContent(tb);
                    }
                    tb.setSelected(true);
                }
            });
            tb.setToggleGroup(group);
        }
        getChildren().addAll(btns);
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setShow(Pane show) {
        this.show = show;
    }

    private void setContent(ToggleButton btn) {
        String text = btn.getAccessibleText();
        String url = String.format("%s%s.fxml", path, text);
        Parent parent = null;
        if (parents.containsKey(url)) {
            parent = parents.get(url);
        } else {
            try {
                parent = FXMLLoader.load(getClass().getResource(url));
                parents.put(url, parent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        show.getChildren().clear();
        show.getChildren().add(parent);
    }
}
