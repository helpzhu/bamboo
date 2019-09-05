package com.bamboo.annotation;

import java.lang.annotation.*;

/**
 * @author XuZhu
 * @version 1.0
 * @desc
 * @data 2019/9/5 10:52
 * @since JDK1.8
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ApiController
public @interface WebApiController {
}
