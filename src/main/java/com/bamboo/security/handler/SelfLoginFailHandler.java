package com.bamboo.security.handler;

import com.bamboo.utils.JsonUtil;
import com.bamboo.utils.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author bamboo
 * @version 1.0
 * @desc 登陆失败handler
 * @date 2019-09-07 21:58
 * @since JDK1.8
 */
@Component
public class SelfLoginFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("result", "401");
        map.put("msg", "登陆失败");
        ResponseUtil.writer(httpServletResponse, JsonUtil.toJson(map), HttpStatus.UNAUTHORIZED.value());
    }
}
