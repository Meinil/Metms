package com.meinil.metms.commons;

/**
 * @Author Meinil
 * @Version 1.0
 */
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;

// 响应结果
public class Result {
    private int code;
    private String message;
    private HashMap<String, ?> data;

    public Result() {
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(HashMap<String, ?> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HashMap<String, ?> getData() {
        return data;
    }

    // 将数据解析为对应的dto
    public <T> T getResult(Class<T> clazz) {
        String key = clazz.getSimpleName().toLowerCase();       // 转小写
        return getResult(JSON.toJSONString(data.get(key)), clazz);
    }

    public <T> T getResult(String json, Class<T> clazz, String ...key) {
        JSONObject jsonObject = JSON.parseObject(json);
        jsonObject = jsonObject.getJSONObject("data");
        for (int i = 0; i < key.length - 1; i++) {
            jsonObject = jsonObject.getJSONObject(key[i]);
        }
        return getResult(jsonObject.getString(key[key.length - 1]), clazz);
    }

    // 获取单个对象
    public static Result getResult(String json) {
        return getResult(json, Result.class);
    }
    public static <T> T getResult(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    // 获取链表
    public <T> List<T> getResultList(Class<T> clazz) {
        String key = clazz.getSimpleName().toLowerCase();       // 转小写
        return  JSON.parseArray(JSON.toJSONString(data.get(key)), clazz);
    }
    public <T> List<T> getResultList(Class<T> clazz, String key) {
        return  JSON.parseArray(JSON.toJSONString(data.get(key)), clazz);
    }
    public static <T> List<T> getResultList(String json, Class<T> clazz) {
        return  JSON.parseArray(json, clazz);
    }
    public static <T> List<T> getResultList(String json, Class<T> clazz, String ...key) {
        JSONObject jsonObject = JSON.parseObject(json);
        jsonObject = jsonObject.getJSONObject("data");
        for (int i = 0; i < key.length - 1; i++) {
            jsonObject = jsonObject.getJSONObject(key[i]);
        }
        return getResultList(jsonObject.getString(key[key.length - 1]), clazz);
    }
}

