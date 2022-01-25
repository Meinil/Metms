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
    private final String path;

    /**
     * 组件类
     */
    private final Class<?> clazz;

    /**
     * !!!子路由, 不要使用, 未完成!!!
     */
    private final Route children;

    /**
     * 是否被缓存
     */
    private final boolean cache;

    public Route(Class<?> clazz) {
        this(clazz, null);
    }

    public Route(Class<?> clazz, Route children) {
        this(clazz, children, true);
    }

    public Route(Class<?> clazz, Route children, boolean cache) {
        this.path = computePath(clazz);
        this.clazz = clazz;
        this.children = children;
        this.cache = cache;
    }

    public String getPath() {
        return path;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public Route getChildren() {
        return children;
    }

    public boolean isCache() {
        return cache;
    }

    private static String computePath(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            View annotation = method.getAnnotation(View.class);
            if (annotation != null) {
                return annotation.path();
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
        return isCache() == route.isCache() && Objects.equals(getPath(), route.getPath()) && Objects.equals(getClazz(), route.getClazz()) && Objects.equals(getChildren(), route.getChildren());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPath(), getClazz(), getChildren(), isCache());
    }
}
