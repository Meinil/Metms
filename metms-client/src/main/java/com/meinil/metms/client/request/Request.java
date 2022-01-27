package com.meinil.metms.client.request;

import com.alibaba.fastjson.JSON;
import com.meinil.metms.client.component.PromptBox;
import com.meinil.metms.client.config.CacheConfig;
import com.meinil.metms.client.model.Result;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * @author Meinil
 */
public class Request {
    private static final HttpClient CLIENT;
    private static String baseUrl = "http://localhost:8080";

    static {
        CLIENT = HttpClient
                .newBuilder()
                .connectTimeout(Duration.ofMillis(5000))
                .build();
    }

    /**
     * get 请求
     * @param path 请求路径
     * @return 请求对象
     */
    public static Result get(String path) {
        return request(path, null);
    }

    /**
     * post 请求
     * @param path  请求路径
     * @param params 请求参数
     * @return  请求对象
     */
    public static Result post(String path, Object params) {
        return request(path, params);
    }

    private static Result request(String path, Object params) {
        try {
            HttpRequest request = null;
            if (params == null) {
                request = HttpRequest
                        .newBuilder()
                        .headers("token", CacheConfig.get("token"))
                        .GET()
                        .uri(new URI(baseUrl + path))
                        .build();
            } else {
                request = HttpRequest
                        .newBuilder()
                        .headers("token", CacheConfig.get("token"))
                        .POST(HttpRequest.BodyPublishers.ofString(JSON.toJSONString(params)))
                        .uri(new URI(baseUrl + path))
                        .build();
            }

            return JSON.parseObject(
                    CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body(),
                    Result.class
            );
        } catch (URISyntaxException | IOException | InterruptedException e) {
            PromptBox.setShow("网络问题", PromptBox.ERROR);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置基础url
     * @param baseUrl 基础url
     */
    public static void setBaseUrl(String baseUrl) {
        Request.baseUrl = baseUrl;
    }
}
