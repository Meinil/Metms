package com.meinil.metms.client.utils;

/**
 * @Author Meinil
 * @Version 1.0
 */
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;

// 响应结果
public class Result {
    private int code;
    private String message;
    private HashMap<String, Object> data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public <T> T getValue(String key, Class<T> clazz) {
        Object obj = data.get(key);
        if (obj instanceof JSONObject) {
            JSONObject value = (JSONObject)data.get(key);
            return value.toJavaObject(clazz);
        }
        return (T)data.get(key);
    }

    public <T> List<T> getListValue(String key, Class<T> clazz) {
        JSONArray obj = (JSONArray)data.get(key);
        return obj.toJavaList(clazz);
    }
}

