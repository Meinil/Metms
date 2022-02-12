package com.meinil.metms.client.utils;

import javafx.concurrent.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class TaskUtils {
    private final static ExecutorService POOL = Executors.newFixedThreadPool(10);

    public static void execute(Task<?> task) {
        POOL.execute(task);
    }

    public static void shutDown() {
        POOL.shutdown();
    }
}
