package com.meinil.metms.client.utils;

import java.util.UUID;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class UUIDUtils {
    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }
}
