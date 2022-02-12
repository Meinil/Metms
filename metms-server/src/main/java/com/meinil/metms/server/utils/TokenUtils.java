package com.meinil.metms.server.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.meinil.metms.server.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author Meinil
 * @Version 1.0
 */

@Component
public class TokenUtils {
    /**
     * 秘钥
     */
    @Value("${token-secret}")
    private String secret;
    /**
     * 分钟
     */
    @Value("${token-time}")
    private int time;
    public String getToken(User user) {
        return JWT.create()
                //payload
                .withClaim("account", user.getAccount())
                .withClaim("power", user.getPower())
                //指定过期时间
                .withExpiresAt(new Date(System.currentTimeMillis() + time * 60 * 1000))
                //signature
                .sign(Algorithm.HMAC384(secret));
    }

    public DecodedJWT verifyToken(String token) {
        return JWT.require(Algorithm.HMAC384(secret)).build().verify(token);
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getSecret() {
        return secret;
    }
}
