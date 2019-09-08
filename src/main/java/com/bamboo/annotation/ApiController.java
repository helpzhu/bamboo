package com.bamboo.annotation;

import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**
 * @author XuZhu
 * @version 1.0
 * @desc
 * @date 2019/9/5 10:51
 * @since JDK1.8
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
public @interface ApiController {
}
