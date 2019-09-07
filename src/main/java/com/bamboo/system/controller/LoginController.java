package com.bamboo.system.controller;

import com.bamboo.annotation.WebApiController;
import com.bamboo.security.MyUserDetailsService;
import com.bamboo.system.api.LoginControllerApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;

@WebApiController
public class LoginController implements LoginControllerApi {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @PostMapping("/login_p")
    @Override
    public void login(String userName, String password) {
        logger.info("登陆,用户名：{},密码：{}", userName, password);
        myUserDetailsService.loadUserByUsername(userName);
        //Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //logger.info("登陆结果：", object);
        //return "success";
    }
}
