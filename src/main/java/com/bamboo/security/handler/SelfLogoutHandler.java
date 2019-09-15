package com.bamboo.security.handler;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019-09-13 21:07
 * @since JDK1.8
 */
@Component
public class SelfLogoutHandler implements LogoutHandler {

    @Autowired
    private SessionRegistry sessionRegistry;

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        String sessionId = httpServletRequest.getRequestedSessionId();
        if (StringUtils.isNotBlank(sessionId)) {
            this.sessionRegistry.removeSessionInformation(sessionId);
        }
    }
}
