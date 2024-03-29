/*
package com.bamboo.system.controller;

import com.bamboo.annotation.WebApiController;
import com.bamboo.base.BaseController;
import com.bamboo.security.service.SelfUserDetailsService;
import com.bamboo.system.api.LoginControllerApi;
import com.bamboo.utils.JsonUtil;
import com.bamboo.utils.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

*/
/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019-09-07 20:18
 * @since JDK1.8
 *//*


@WebApiController
@RequestMapping("/bamboo")
public class LoginController extends BaseController implements LoginControllerApi {

    private static final Logger logger = LoggerFactory.getLogger(Class.class);

    private RequestCache requestCache = new HttpSessionRequestCache();

    @Autowired
    private SelfUserDetailsService selfUserDetailsService;

    @GetMapping("/login")
    @Override
    public void login(String userName, String password) throws Exception {
        logger.info("登陆,用户名：{},密码：{}", userName, password);
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", 200);
            map.put("result", "登陆失败，用户名或密码不能为空");
            ResponseUtil.writer(this.response, JsonUtil.toJson(map), HttpStatus.OK.value());
        }

        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String redirectUrl = savedRequest.getRedirectUrl();
            logger.info("引发跳转的请求是：{}", redirectUrl);
        }

        this.selfUserDetailsService.loadUserByUsername(userName);
    }

    @GetMapping("/logout")
    @Override
    public void logout() {
        logger.info("注销登陆");
    }
}
*/
