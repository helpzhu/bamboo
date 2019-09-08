package com.bamboo.base;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019-09-08 09:45
 * @since JDK1.8
 */
public class BaseController {

    @Autowired
    public HttpServletRequest request;

    @Autowired
    public HttpServletResponse response;
}
