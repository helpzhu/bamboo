package com.bamboo.system.dao;

import com.bamboo.system.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @data 2019/9/4 15:06
 * @since JDK1.8
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> getUsersByUserName(String userName);
}
