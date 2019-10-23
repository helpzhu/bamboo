package com.bamboo.utils;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019/9/30 11:27
 * @since JDK1.8
 */
public class BambooUtil {

    public static String getCurrentUser() {
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return object == null ? null : JsonUtil.toJson(object);
    }
}
