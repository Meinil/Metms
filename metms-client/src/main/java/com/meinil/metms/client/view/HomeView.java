package com.meinil.metms.client.view;

import com.meinil.jfxrouter.Route;
import com.meinil.jfxrouter.Router;
import com.meinil.jfxrouter.RouterView;
import com.meinil.jfxrouter.annotation.View;
import com.meinil.metms.client.MetmsClientApplication;
import com.meinil.metms.client.controller.HomeController;
import com.meinil.metms.client.model.Menu;
import com.meinil.metms.client.model.User;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.util.StringConverter;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class HomeView {

    private ImageView avatar;
    private AnchorPane left;
    private ListView<com.meinil.metms.client.model.Menu> listMenu;
    private Router homeRouter = new Router(
            new Route(AdminView.class),
            new Route(LoginView.class)
    );

    @View(path = "/home", controller = HomeController.class)
    public Node getView(RouterView routerView, User user) {
        MetmsClientApplication.setStageSize(1470, 850);

        left = new AnchorPane();
        left.setPrefWidth(200);
        left.setStyle("-fx-background-color: #FFFFFF00");

        Image image = new Image(getClass().getResource("/static/image/default_avatar.png").toExternalForm());
        avatar = new ImageView(image);
        Circle circle = new Circle(32);
        circle.setCenterX(32);
        circle.setCenterY(32);
        avatar.setClip(circle);
        avatar.setFitHeight(64);
        avatar.setPreserveRatio(true);
        AnchorPane.setTopAnchor(avatar, 170.0);
        AnchorPane.setRightAnchor(avatar, 0.0);

        listMenu = getMenu(user.getPower());

        left.getChildren().addAll(listMenu, avatar);
        left.translateYProperty().bind(routerView.heightProperty().divide(2).subtract(left.heightProperty().divide(2)));

        AnchorPane box = new AnchorPane();
        box.prefWidthProperty().bind(routerView.prefWidthProperty());
        box.prefHeightProperty().bind(routerView.prefHeightProperty());
        RouterView homeRouterView = new RouterView();
        homeRouterView.prefWidthProperty().bind(box.prefWidthProperty());
        homeRouterView.prefHeightProperty().bind(box.prefHeightProperty());

        this.homeRouter.mount(homeRouterView);
        box.getChildren().addAll(homeRouterView, left);
        return box;
    }

    public Router getHomeRouter() {
        return homeRouter;
    }

    public ListView<com.meinil.metms.client.model.Menu> getListMenu() {
        return listMenu;
    }
    /**
     * 获取菜单
     * @param power 权力等级
     * @return 菜单
     */
    private ListView<com.meinil.metms.client.model.Menu> getMenu(int power) {
        ListView<com.meinil.metms.client.model.Menu> menu = new ListView<>();
        switch (power) {
            case 0 -> menu.getItems().add(new Menu("用户管理", "/admin/user"));
            case 2 -> menu.getItems().addAll(
                    new Menu("发布计划", "/teacher/release"),
                    new Menu("审批计划", "/teacher/approve"),
                    new Menu("我的学生", "/teacher/student")
            );
            case 3 -> menu.getItems().addAll(
                    new Menu("最近计划", "/student/recently"),
                    new Menu("我的导师", "/student/release"),
                    new Menu("我的提交", "/student/approve")
            );
            default -> {
            }
        }

        menu.setCellFactory(TextFieldListCell.forListView(new StringConverter<Menu>() {
            @Override
            public String toString(Menu m) {
                return m.getName();
            }

            @Override
            public Menu fromString(String string) {
                return null;
            }
        }));

        return menu;
    }

    public ImageView getAvatar() {
        return avatar;
    }

    public AnchorPane getLeft() {
        return left;
    }
}
