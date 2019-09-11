package com.bamboo.system.service;

import com.bamboo.system.condition.SelfRoleCondition;
import com.bamboo.system.domain.SelfRole;
import org.springframework.data.domain.Page;

/**
 * @author XuZhu
 * @version 1.0
 * @desc
 * @date 2019/9/11 15:55
 * @since JDK1.8
 */
public interface RoleService {

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    String insertRole(SelfRole role);

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    String updateRole(SelfRole role);

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    String deleteRole(Long roleId);

    /**
     * 分页查询角色
     *
     * @param condition
     * @return
     */
    Page<SelfRole> getRolePaging(SelfRoleCondition condition);
}
