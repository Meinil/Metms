package com.meinil.metms.client.config;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class SystemConfig {
    private static final double WIDTH;
    private static final double HEIGHT;

    static {
        Rectangle2D screenRectangle = Screen.getPrimary().getBounds();
        WIDTH = screenRectangle.getWidth();
        HEIGHT = screenRectangle.getHeight();
    }

    /**
     * 获取屏幕宽度
     * @return 返回系统宽高
     */
    public static double getWidth() {
        return WIDTH;
    }

    /**
     * 获取屏幕高度
     * @return 返回屏幕高度
     */
    public static double getHeight() {
        return HEIGHT;
    }
}
