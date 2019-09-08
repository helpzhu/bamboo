package com.bamboo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019-09-08 10:02
 * @since JDK1.8
 */
public class ResponseUtil {

    private static final Logger logger = LoggerFactory.getLogger(ResponseUtil.class);

    public static void writer(HttpServletResponse response, String json, Integer status) throws IOException {
        try {
            response.setHeader("Content-Type", "application/json;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(status);
            PrintWriter writer = response.getWriter();
            writer.write(json);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            logger.error("response返回出错", e);
            throw e;
        }
    }
}
