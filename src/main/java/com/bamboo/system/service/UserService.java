package com.bamboo.system.service;

import com.bamboo.system.entity.User;

/**
 * @author XuZhu
 * @version 1.0
 * @desc
 * @data 2019/9/5 13:44
 * @since JDK1.8
 */
public interface UserService {

    User getUserByUserName(String UserName);
}
