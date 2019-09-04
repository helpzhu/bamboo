package com.bamboo.system.controller;

import com.bamboo.system.dao.UserRepository;
import com.bamboo.system.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @data 2019/9/4 15:08
 * @since JDK1.8
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getUserByName")
    @ResponseBody
    public List<User> getUserByName() {
        List<User> userList = this.userRepository.getUsersByUserName("admin");
        return userList;
    }

    @GetMapping("/getAllUser")
    @ResponseBody
    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }
}
