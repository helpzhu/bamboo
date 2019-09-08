package com.bamboo.system.service.impl;

import com.bamboo.system.entity.User;
import com.bamboo.system.repository.UserRepository;
import com.bamboo.system.service.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019/9/5 13:44
 * @since JDK1.8
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByUserName(String userName) {
        List<User> userList = this.userRepository.getUsersByUserName(userName);

        if (CollectionUtils.isNotEmpty(userList)) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }
}
