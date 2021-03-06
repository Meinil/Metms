package com.meinil.jfxrouter;

import com.meinil.jfxrouter.annotation.FXInit;
import com.meinil.jfxrouter.annotation.FXRouter;
import com.meinil.jfxrouter.annotation.FXView;
import com.meinil.jfxrouter.annotation.View;
import javafx.application.Platform;
import javafx.scene.Node;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class Router {
    /**
     * 组件缓存
     * key 路由
     * Node 组件
     */
    private final Map<Route, Node> NODE_CACHE = new HashMap<>();
    private final Map<Route, Object> CONTROLLER_CACHE = new HashMap<>();

    /**
     * 当前路由
     */
    private Route current;

    /**
     * 所有的路由
     */
    private final Route[] routes;

    /**
     * 路由出口
     */
    private RouterView routerView;

    /**
     * 路由更新之前
     */
    private Function<Route, Boolean> beforeUpdateView;

    /**
     * 默认路由
     */
    private Route defaultRoute;

    /**
     * @param routes 所有的路由
     */
    public Router(Route ...routes) {
        this.routes = routes;
    }

    /**
     * 挂载更新之后的组件显示的位置
     * @param routerView 显示位置
     */
    public void mount(RouterView routerView) {
        this.routerView = routerView;
    }

    /**
     * 去往某个路由
     * @param path 路由
     * @param params 参数这个参数将会由@View接受到
     */
    public void push(String path, Object ...params) {
        if (path == null || "".equals(path)) {
            throw new RuntimeException("path不能为null或空字符");
        }
        if (routerView == null) {
            throw new RuntimeException("RouterView不能为空请为Router挂载RouterView");
        }
        for (Route route : routes) {
            if (route.getPath().equals(path)) {
                // 防止重复更新路由
                if (route == current) {
                    return;
                }
                updateView(route, params);
                current = route;
                return;
            }
        }
        // 没有匹配的路由,跳转至默认路由
        if (defaultRoute != null) {
            updateView(defaultRoute, params);
        }
    }

    /**
     * 设置路由拦截器 路由拦截器中返回false代表拦截此次路由 否则正常跳转
     * @param beforeUpdateView 路由拦截器
     */
    public void setBeforeUpdateView(Function<Route, Boolean> beforeUpdateView) {
        this.beforeUpdateView = beforeUpdateView;
    }

    /**
     * 设置默认路由
     * @param defaultPath  默认路径
     */
    public void setDefaultRoute(String defaultPath) {
        this.defaultRoute = getRoute(defaultPath);
    }

    /**
     * 更新界面
     * @param route 路由组件
     */
    private void updateView(Route route, Object ...params) {
        boolean isStart = true;
        if (beforeUpdateView != null) {
            isStart = beforeUpdateView.apply(route);
        }
        if (isStart) {
            Platform.runLater(() -> {
                // 清理原组件
                routerView.getChildren().clear();
                // 获取组件 优先获取缓存中的组件
                Node node;
                if (route.isCache()) {
                    node = NODE_CACHE.get(route);
                    if (node == null) {
                        node = getInstance(route, params);
                    }
                } else {
                    node = getInstance(route, params);
                }
                // 添加新组件
                routerView.getChildren().add(node);
            });
        }
    }

    /**
     * 获取组件实例
     * @param route 路由组件
     * @return 组件实例
     */
    private Node getInstance(Route route, Object ...params) {
        Class<?> clazz = route.getNodeClazz();
        try {
            Object instance = clazz.getConstructor().newInstance();
            Node node = getView(instance, params);
            if (route.isCache()) {
                // 缓存组件
                NODE_CACHE.put(route, node);
            }
            // 为控制器注入Router     控制器只会生成一次，
            if (!CONTROLLER_CACHE.containsKey(route)) {
                Object controller = getController(route, instance);
                if (controller != null) {
                    CONTROLLER_CACHE.put(route, controller);
                }
            }
            return node;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取视图
     * @param obj 视图对象
     * @param params 视图的参数
     * @return 视图
     */
    private Node getView(Object obj, Object ...params) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        Method[] methods = obj.getClass().getMethods();
        for (Method method : methods) {
            View annotation = method.getAnnotation(View.class);
            if (annotation != null) {
                if (method.getParameterCount() == params.length) {
                    return (Node) method.invoke(obj, params);
                }
                // 注入RouterView
                Object[] args = new Object[method.getParameterCount()];
                args[0] = routerView;
                System.arraycopy(params, 0, args, 1, args.length - 1);
                return (Node) method.invoke(obj, args);
            }
        }
        return null;
    }

    /**
     * 获取组件控制器
     * @return 控制器
     */
    private Object getController(Route route, Object view) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz = route.getControllerClazz();
        if(clazz.equals(View.class)){
            return null;
        }
        Object instance = clazz.getConstructor().newInstance();
        // 注入Router和视图对象
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            FXRouter a1 = field.getAnnotation(FXRouter.class);
            if (a1 != null) {
                field.setAccessible(true);
                field.set(instance, this);
            } else {
                FXView a2 = field.getAnnotation(FXView.class);
                if (a2 != null) {
                    field.setAccessible(true);
                    field.set(instance, view);
                }
            }
        }

        // 调用初始化方法
        Method[] methods = clazz.getMethods();
        for(Method method : methods) {
            FXInit annotation = method.getAnnotation(FXInit.class);
            if (annotation != null) {
                method.invoke(instance);
            }
        }
        return instance;
    }

    /**
     * 获取指定字符串的路由
     * @param path 路由字符串
     */
    private Route getRoute(String path) {
        for (Route route : routes) {
            if (route.getPath().equals(path)) {
                return route;
            }
        }
        throw new RuntimeException("找不到该路由");
    }
}
