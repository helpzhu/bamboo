package com.bamboo.security.handler;

import com.bamboo.base.ResponseVo;
import com.bamboo.utils.JsonUtil;
import com.bamboo.utils.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException exception) throws IOException {

        String message;
        if (exception instanceof UsernameNotFoundException) {
            message = "用户不存在！";
        } else if (exception instanceof BadCredentialsException) {
            message = "用户名或密码错误！";
        } else if (exception instanceof LockedException) {
            message = "用户已被锁定！";
        } else if (exception instanceof DisabledException) {
            message = "用户不可用！";
        } else if (exception instanceof AccountExpiredException) {
            message = "账户已过期！";
        } else if (exception instanceof CredentialsExpiredException) {
            message = "用户密码已过期！";
        } else {
            message = "认证失败，请联系网站管理员！";
        }

        ResponseUtil.writer(httpServletResponse, JsonUtil.toJson(ResponseVo.failed(message)), HttpStatus.UNAUTHORIZED.value());
    }
}
