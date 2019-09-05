package com.bamboo.system.controller;

import com.bamboo.annotation.WebApiController;
import com.bamboo.system.api.UserControllerApi;
import com.bamboo.system.repository.UserRepository;
import com.bamboo.system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @data 2019/9/4 15:08
 * @since JDK1.8
 */
@WebApiController
@RequestMapping("/user")
public class UserController implements UserControllerApi {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getUserByName")
    public List<User> getUserByName() {
        List<User> userList = this.userRepository.getUsersByUserName("admin");
        return userList;
    }

    @GetMapping("/getAllUser")
    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }
}
