package com.bamboo.system.service;

import com.bamboo.system.entity.User;

import java.util.List;

/**
 * @author XuZhu
 * @version 1.0
 * @desc
 * @date 2019/9/5 13:44
 * @since JDK1.8
 */
public interface UserService {

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    User getUserByUserName(String userName);

    /**
     * 查询所有用户信息
     * @return
     */
    List<User> getAllUser();
}
