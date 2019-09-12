package com.bamboo.system.service.impl;

import com.bamboo.system.dao.UserRoleRepository;
import com.bamboo.system.domain.UserRole;
import com.bamboo.system.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019/9/12 11:39
 * @since JDK1.8
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserRoleServiceImpl implements UserRoleService {

    private static final Logger logger = LoggerFactory.getLogger(UserRoleServiceImpl.class);

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public void insertUserRole(UserRole userRole) throws Exception {
        try {
            this.userRoleRepository.save(userRole);
        } catch (Exception e) {
            logger.error("添加用户角色信息出错", e);
            throw e;
        }
    }

    @Override
    public void updateUserRole(UserRole userRole) throws Exception {
        try {
            this.userRoleRepository.save(userRole);
        } catch (Exception e) {
            logger.error("修改用户角色信息出错", e);
            throw e;
        }
    }

    @Override
    public void deleteUserRole(Long userRoleId) throws Exception {
        try {
            this.userRoleRepository.deleteById(userRoleId);
        } catch (Exception e) {
            logger.error("删除用户角色信息出错", e);
            throw e;
        }
    }
}
