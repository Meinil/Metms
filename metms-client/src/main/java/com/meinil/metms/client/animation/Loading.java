package com.meinil.metms.client.animation;

import javafx.scene.image.ImageView;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class Loading {
    private static ImageView iv;

    public static void setLoading(ImageView iv) {
        Loading.iv = iv;
    }

    public static void show() {
        iv.setVisible(true);
    }

    public static void hide() {
        iv.setVisible(false);
    }
}
