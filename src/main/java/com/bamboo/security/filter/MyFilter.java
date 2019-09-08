package com.bamboo.security.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;


/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019/9/6 14:48
 * @since JDK1.8
 */
public class MyFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("自定义filter");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
