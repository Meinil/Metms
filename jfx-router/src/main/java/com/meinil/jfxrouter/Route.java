package com.meinil.jfxrouter;

import com.meinil.jfxrouter.annotation.View;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class Route {
    /**
     * 路由路径
     */
    private String path;

    /**
     * 组件类
     */
    private final Class<?> nodeClazz;

    /**
     * 控制器类
     */
    private Class<?> controllerClazz;

    /**
     * !!!子路由, 不要使用, 未完成!!!
     */
    private final Route children;

    /**
     * 是否被缓存
     */
    private final boolean cache;

    public Route(Class<?> nodeClazz) {
        this(nodeClazz, null);
    }

    public Route(Class<?> nodeClazz, Route children) {
        this(nodeClazz, children, true);
    }

    public Route(Class<?> nodeClazz, Route children, boolean cache) {
        computePath(nodeClazz);
        this.nodeClazz = nodeClazz;
        this.children = children;
        this.cache = cache;
    }

    public String getPath() {
        return path;
    }

    public Class<?> getNodeClazz() {
        return nodeClazz;
    }

    public Class<?> getControllerClazz() {
        return controllerClazz;
    }

    public Route getChildren() {
        return children;
    }

    public boolean isCache() {
        return cache;
    }

    private void computePath(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            View annotation = method.getAnnotation(View.class);
            if (annotation != null) {
                path = annotation.path();                   // 路由路径
                controllerClazz = annotation.controller();  // 控制器
                return;
            }
        }
        throw new RuntimeException(String.format("请在%s的某一方法上加上@View注解", clazz.getName()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Route route)) {
            return false;
        }
        return isCache() == route.isCache() && Objects.equals(getPath(), route.getPath()) && Objects.equals(getNodeClazz(), route.getNodeClazz()) && Objects.equals(getChildren(), route.getChildren());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPath(), getNodeClazz(), getChildren(), isCache());
    }
}
