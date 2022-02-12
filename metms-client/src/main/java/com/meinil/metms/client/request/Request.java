package com.meinil.metms.client.request;

import com.alibaba.fastjson.JSON;
import com.meinil.metms.client.animation.PromptBox;
import com.meinil.metms.client.utils.Config;
import com.meinil.metms.client.utils.Result;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.function.Consumer;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class Request {
    private final static HttpClient CLIENT = HttpClient
            .newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();
    private static String baseURL = "http://localhost:8080";

    public static void setBaseURL(String baseURL) {
        Request.baseURL = baseURL;
    }

    public static Result get(String path) {
        try {
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(new URI(baseURL + path))
                    .header("token", (String) Config.get("token"))
                    .GET()
                    .build();
            return JSON.parseObject(CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body(), Result.class);
        } catch (URISyntaxException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Result post(String path, Object params) {
        try {
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(new URI(baseURL + path))
                    .header("token", (String) Config.get("token"))
                    .POST(HttpRequest.BodyPublishers.ofString(JSON.toJSONString(params)))
                    .build();
            return JSON.parseObject(CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body(), Result.class);
        } catch (URISyntaxException | InterruptedException | IOException e) {
            PromptBox.show("服务器不可达", PromptBox.ERROR);
            e.printStackTrace();
        }
        return null;
    }

    public static void get(String path, Consumer<HashMap<String, Object>> consumer) {
        request(path,null, consumer);
    }
    public static void post(String path, Object params, Consumer<HashMap<String, Object>> consumer) {
        request(path, params, consumer);
    }

    private static void request(String path, Object params, Consumer<HashMap<String, Object>> consumer) {
        try {
            HttpRequest request;
            if (params == null) {
                request = HttpRequest
                        .newBuilder()
                        .uri(new URI(baseURL + path))
                        .header("token", (String) Config.get("token"))
                        .POST(HttpRequest.BodyPublishers.ofString(JSON.toJSONString(params)))
                        .build();
            } else {
                request = HttpRequest
                        .newBuilder()
                        .uri(new URI(baseURL + path))
                        .header("token", (String) Config.get("token"))
                        .build();
            }

            CLIENT
                    .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(res -> {
                        Result result = JSON.parseObject(res.body(), Result.class);
                        switch (result.getCode()) {
                            case 100 -> {
                                PromptBox.show("账号或密码错误", PromptBox.ERROR);
                            }
                            case 200 -> {
                                consumer.accept(result.getData());
                                PromptBox.show("请求成功", PromptBox.SUCCESS);
                            }
                            case 400 -> {
                                PromptBox.show(result.getMessage(), PromptBox.ERROR);
                            }
                        }
                        return res;
                    })
                    .exceptionally(err -> {
                        PromptBox.show("服务器不可达", PromptBox.ERROR);
                        err.printStackTrace();
                        return null;
                    });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
