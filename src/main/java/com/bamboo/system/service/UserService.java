package com.bamboo.system.service;

import com.bamboo.system.condition.SelfUserCondition;
import com.bamboo.system.domain.SelfUser;
import org.springframework.data.domain.Page;

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
     * 添加用户
     * @param user
     * @return
     */
    String insertUser(SelfUser user) throws Exception;

    /**
     * 修改用户
     * @param user
     * @return
     */
    String updateUser(SelfUser user) throws Exception;

    /**
     * 删除用户
     * @param userId
     * @return
     */
    String deleteUser(Long userId) throws Exception;

    /**
     * 根据用户账号查询用户信息
     * @param userAccount
     * @return
     */
    SelfUser getUserByUserAccount(String userAccount);

    /**
     * 查询所有用户信息
     * @return
     */
    List<SelfUser> findAll();

    /**
     * 分页查询用户信息
     * @param condition
     * @return
     */
    Page<SelfUser> getUserPaging(SelfUserCondition condition) throws Exception;
}
