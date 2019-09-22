package com.bamboo.security.filter;

import com.bamboo.base.ResponseVo;
import com.bamboo.utils.JsonUtil;
import com.bamboo.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019/9/6 14:48
 * @since JDK1.8
 */
public class SelfLoginFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(SelfLoginFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("自定义filter");
        String url = ((HttpServletRequest) servletRequest).getRequestURI();
        List<String> urlList = Arrays.asList("/login", "/bamboo/login");
        if (!urlList.contains(url) && null == SecurityContextHolder.getContext().getAuthentication()) {
//            HttpServletResponse response = (HttpServletResponse) servletResponse;
//            ResponseUtil.writer(response, JsonUtil.toJson(ResponseVo.failed("未登陆")), 460);
            servletResponse.setContentType("application/json;charset=utf-8");
//            servletResponse.setContentType();
            servletResponse.getWriter().write(JsonUtil.toJson(ResponseVo.failed("未登陆")));
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
