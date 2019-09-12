package com.bamboo.system.service;

import com.bamboo.system.domain.UserRole;

/**
 * @author XuZhu
 * @version 1.0
 * @desc
 * @date 2019/9/12 11:38
 * @since JDK1.8
 */
public interface UserRoleService {

    void insertUserRole(UserRole userRole) throws Exception;

    void updateUserRole(UserRole userRole) throws Exception;

    void deleteUserRole(Long userRoleId) throws Exception;
}
