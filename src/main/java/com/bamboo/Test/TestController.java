package com.bamboo.Test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @data 2019/9/3 20:07
 * @since JDK1.8
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello";
    }
}
