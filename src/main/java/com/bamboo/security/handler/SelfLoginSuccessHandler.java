package com.bamboo.security.handler;

import com.bamboo.utils.JsonUtil;
import com.bamboo.utils.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author bamboo
 * @version 1.0
 * @desc 登陆成功handler
 * @date 2019-09-07 21:49
 * @since JDK1.8
 */
@Component
public class SelfLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Cookie token = new Cookie("bamboo", UUID.randomUUID().toString());//TokenUtils.createToken(httpServletRequest);
        httpServletResponse.addCookie(token);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("result", "登陆成功");
        ResponseUtil.writer(httpServletResponse, JsonUtil.toJson(map), HttpStatus.OK.value());
    }
}
