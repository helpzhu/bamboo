package com.bamboo.security.handler;

import com.bamboo.base.ResponseVo;
import com.bamboo.utils.JsonUtil;
import com.bamboo.utils.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author bamboo
 * @version 1.0
 * @desc 登陆成功handler
 * @date 2019-09-07 21:49
 * @since JDK1.8
 */
@Component
public class SelfLoginSuccessHandler implements AuthenticationSuccessHandler {//AuthenticationSuccessHandler {

    @Autowired
    private SessionRegistry sessionRegistry;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        String sessionId = details.getSessionId();
        if (StringUtils.isNotBlank(sessionId)) {
            sessionRegistry.removeSessionInformation(sessionId);
            sessionRegistry.registerNewSession(sessionId, authentication.getPrincipal());
        }

        ConcurrentSessionControlAuthenticationStrategy authenticationStrategy = new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry);

        // 手动设置最大并发登录数量，因为在 sessionManagement 中设置的 maximumSessions
        // 只对账号密码登录的方式生效 =。=
        //authenticationStrategy.setMaximumSessions(febsSecurityProperties.getSession().getMaximumSessions());
        authenticationStrategy.onAuthentication(authentication, httpServletRequest, httpServletResponse);

        // 会帮我们跳转到上一次请求的页面上
        //super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);

        //Cookie token = new Cookie("bamboo", JsonUtil.toJson(authentication.getPrincipal()));//TokenUtils.createToken(httpServletRequest);
        //httpServletResponse.addCookie(token);
        ResponseUtil.writer(httpServletResponse, JsonUtil.toJson(ResponseVo.success("登陆成功")), HttpStatus.OK.value());
    }
}
