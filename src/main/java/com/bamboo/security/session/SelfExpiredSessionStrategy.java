package com.bamboo.security.session;

import com.bamboo.utils.JsonUtil;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理 session过期
 * 导致 session 过期的原因有：
 * 1. 并发登录控制
 * 2. 被踢出
 */
@Component
public class SelfExpiredSessionStrategy implements SessionInformationExpiredStrategy {

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("result", "登录已失效");
        //event.getResponse().setContentType(FebsConstant.JSON_UTF8);
        event.getResponse().getWriter().write(JsonUtil.toJson(map));
    }

}
