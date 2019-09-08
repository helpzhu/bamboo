package com.bamboo.utils;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019-09-07 20:18
 * @since JDK1.8
 */
public class JsonUtil {

    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    private JsonUtil() {
    }

    /**
     * 对象转换为json字符串
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        String json = null;
        if (null != object) {
            json = gson.toJson(object);
        }
        return json;
    }

    /**
     * json字符串转换为指定对象
     * @param json
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> tClass) {
        T t = null;
        if (StringUtils.isNotBlank(json)) {
            t = gson.fromJson(json, tClass);
        }
        return t;
    }
}
