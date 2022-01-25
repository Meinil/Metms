package com.meinil.metms.client.request;

import com.alibaba.fastjson.JSONObject;
import com.meinil.metms.commons.Path;

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
    public static HttpResponse<String> get(String path) {
        try {
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .GET()
                    .uri(new URI(baseUrl + path))
                    .build();
            return CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post 请求
     * @param path  请求路径
     * @param params 请求参数
     * @return  请求对象
     */
    public static HttpResponse<String> post(String path, JSONObject params) {
        try {
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(params.toJSONString()))
                    .uri(new URI(baseUrl + path))
                    .build();
            return CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | IOException | InterruptedException e) {
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
