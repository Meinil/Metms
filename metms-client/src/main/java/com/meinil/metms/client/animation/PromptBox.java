package com.meinil.metms.client.animation;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class PromptBox {
    private static Region icon;
    private static Text text;

    private final static TranslateTransition show;
    private final static TranslateTransition hide;

    public final static SVGPath SUCCESS;
    public final static SVGPath ERROR;
    public final static SVGPath INFO;

    static {
        SUCCESS = new SVGPath();
        SUCCESS.setContent("M512.330936 956.628798c-245.847116 0-445.150388-199.711995-445.150388-446.074961 0-246.35757 199.303272-446.074961 445.150388-446.074961 245.851513 0 445.150388 199.717192 445.150388 446.074961C957.481324 756.915803 758.183248 956.628798 512.330936 956.628798zM762.171144 379.680768l-31.226828-30.636229c-17.249905-16.918929-45.213545-16.918929-62.459852 0L449.871083 563.493943l-93.690278-91.908886c-17.249905-16.918929-45.213545-16.918929-62.459852 0l-31.230426 30.636229c-17.249905 16.918929-17.249905 44.352729 0 61.272657l124.917106 122.53632c0 0.003598 0 0.003598 0 0.003598l31.230426 30.636229c17.249905 16.918929 45.213545 16.918929 62.46345 0l281.068036-275.718663c17.250704-16.923326 17.250704-44.35173 0.000799-61.271658L762.171144 379.680768z");

        ERROR = new SVGPath();
        ERROR.setContent("M875.432137 148.567863C677.341654-49.522621 351.450858-49.522621 153.360374 148.567863s-198.090484 523.981279 0 722.071763 523.981279 198.090484 722.071763 0 198.090484-517.591264 0-722.071763z m-230.040561 587.881435L511.201248 602.25897l-134.190328 134.190328c-25.560062 25.560062-63.900156 25.560062-89.460218 0s-25.560062-63.900156 0-89.460218L421.74103 512.798752 287.550702 378.608424c-25.560062-25.560062-25.560062-63.900156 0-89.460218 25.560062-25.560062 63.900156-25.560062 89.460218 0L511.201248 423.338534l134.190328-134.190328c25.560062-25.560062 63.900156-25.560062 89.460218 0s25.560062 63.900156 0 89.460218L600.661466 512.798752l134.190328 134.190328c25.560062 25.560062 25.560062 63.900156 0 89.460218-25.560062 25.560062-63.900156 25.560062-89.460218 0z");

        INFO = new SVGPath();
        INFO.setContent("M508.74077 65.370471c-247.026716 0-447.274148 200.247432-447.274148 447.274148 0 247.028515 200.247432 447.274148 447.274148 447.274148s447.274148-200.245434 447.274148-447.274148C956.014918 265.617903 755.767486 65.370471 508.74077 65.370471zM509.450888 825.736543c-34.495812 0-62.461451-26.162862-62.461451-58.442176 0-32.277716 27.965639-58.442376 62.461451-58.442376 34.506804 0 62.471444 26.16466 62.471444 58.442376C571.922333 799.573881 543.957693 825.736543 509.450888 825.736543zM621.45475 338.605817l-49.630151 276.31326c0 0.306592 0.097734 0.5906 0.097734 0.895393 0 32.279514-27.965639 58.442176-62.471444 58.442176-34.495812 0-62.461451-26.162862-62.461451-58.442176 0-0.304993 0.087541-0.5896 0.097734-0.895393l-49.641943-276.31326 0.699326 0c-0.437304-3.800024-0.699326-7.621832-0.699326-11.509596 0-58.093613 50.144002-105.179688 112.004861-105.179688s112.003861 47.086076 112.003861 105.179688c0 3.888564-0.262022 7.709573-0.699126 11.509596L621.45475 338.605817z");
    }

    static {
        Duration duration = new Duration(300);

        show = new TranslateTransition(duration);
        show.setFromY(-55);
        show.setToY(0);

        hide = new TranslateTransition(duration);
        hide.setDelay(new Duration(2000));
        hide.setFromY(0);
        hide.setToY(-55);
    }

    public static void setPromptBox(HBox promptBox) {
        icon = (Region) promptBox.lookup("#icon");
        text = (Text) promptBox.lookup("#text");
        show.setNode(promptBox);
        show.setOnFinished(event -> {
            hide.play();
        });
        hide.setNode(promptBox);
    }

    public static void show(String message, SVGPath type) {
        if (show.getStatus().equals(Animation.Status.RUNNING)) {
            show.stop();
        } else if (hide.getStatus().equals(Animation.Status.RUNNING)){
            hide.stop();
        }

        if (type.equals(SUCCESS)) {
            icon.setStyle("-fx-background-color: #5FE164");
        } else if(type.equals(ERROR)) {
            icon.setStyle("-fx-background-color: #DB5860");
        } else {
            icon.setStyle("-fx-background-color: #409EFF");
        }
        text.setText(message);

        if (hide.getStatus().equals(Animation.Status.RUNNING)) {
            hide.stop();
        }
        show.play();
    }
}
