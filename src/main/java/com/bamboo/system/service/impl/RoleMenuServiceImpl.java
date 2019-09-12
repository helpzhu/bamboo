package com.bamboo.system.service.impl;

import com.bamboo.system.dao.RoleMenuRepository;
import com.bamboo.system.domain.RoleMenu;
import com.bamboo.system.service.RoleMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019/9/12 11:40
 * @since JDK1.8
 */
@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    private static final Logger logger = LoggerFactory.getLogger(RoleMenuServiceImpl.class);

    @Autowired
    private RoleMenuRepository roleMenuRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertRoleMenu(RoleMenu roleMenu) throws Exception {
        try {
            this.roleMenuRepository.save(roleMenu);
        } catch (Exception e) {
            logger.error("添加角色菜单关联信息出错", e);
            throw e;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateRoleMenu(RoleMenu roleMenu) throws Exception {
        try {
            this.roleMenuRepository.save(roleMenu);
        } catch (Exception e) {
            logger.error("修改角色菜单关联信息出错", e);
            throw e;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteRoleMenu(Long roleMenuId) throws Exception {
        try {
            this.roleMenuRepository.deleteById(roleMenuId);
        } catch (Exception e) {
            logger.error("删除角色菜单关联信息出错", e);
            throw e;
        }
    }
}
