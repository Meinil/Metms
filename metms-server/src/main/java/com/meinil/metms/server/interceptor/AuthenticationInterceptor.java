package com.meinil.metms.server.interceptor;

import com.auth0.jwt.exceptions.*;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.meinil.metms.server.utils.Result;
import com.meinil.metms.server.annotation.PassToken;
import com.meinil.metms.server.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Author Meinil
 * @Version 1.0
 */

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenUtils utils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod method = (HandlerMethod) handler;
        PassToken methodAnnotation = method.getMethodAnnotation(PassToken.class);
        if (methodAnnotation == null) {     // 不需要验证
            return true;
        }
        Result result = new Result();
        DecodedJWT tokenInfo = null;
        try {
            String token = request.getHeader("token");
            tokenInfo = utils.verifyToken(token);
        }catch (SignatureVerificationException e) {
            e.printStackTrace();
            result.setMessage("无效签名");
        }catch (TokenExpiredException e) {
            e.printStackTrace();
            result.setMessage("token过期");
        }catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            result.setMessage("token算法不一致");
        }catch (Exception e) {
            e.printStackTrace();
            result.setMessage("无效token");
        }
        // 检查权限
        if(tokenInfo != null) {
            Integer power = tokenInfo.getClaim("power").asInt();
            if (power > methodAnnotation.value()) {
                result.setMessage("权限不足");
            }
        }

        //如果有异常，阻止执行
        if(result.getMessage() != null) {
            //转为json，发回给前端
            result.setCode(400);
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.println(result);
            writer.close();
            return false;
        }

        return true;
    }
}