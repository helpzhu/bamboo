package com.bamboo.system.service;

import com.bamboo.system.domain.RoleMenu;

/**
 * @author XuZhu
 * @version 1.0
 * @desc
 * @date 2019/9/12 11:39
 * @since JDK1.8
 */
public interface RoleMenuService {

    void insertRoleMenu(RoleMenu roleMenu) throws Exception;

    void updateRoleMenu(RoleMenu roleMenu) throws Exception;

    void deleteRoleMenu(Long roleMenuId) throws Exception;
}
