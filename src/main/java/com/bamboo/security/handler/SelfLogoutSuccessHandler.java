package com.bamboo.security.handler;

import com.bamboo.base.ResponseVo;
import com.bamboo.utils.JsonUtil;
import com.bamboo.utils.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author bamboo
 * @version 1.0
 * @desc 注销成功handler
 * @date 2019-09-07 21:59
 * @since JDK1.8
 */
@Component
public class SelfLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        ResponseUtil.writer(response, JsonUtil.toJson(ResponseVo.success("注销成功")), HttpStatus.OK.value());
    }
}
