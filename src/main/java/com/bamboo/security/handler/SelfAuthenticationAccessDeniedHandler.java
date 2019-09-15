package com.bamboo.security.handler;

import com.bamboo.base.ResponseVo;
import com.bamboo.utils.JsonUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019-09-07 21:58
 * @since JDK1.8
 */
public class SelfAuthenticationAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JsonUtil.toJson(ResponseVo.failed("没有权限")));
    }
}
