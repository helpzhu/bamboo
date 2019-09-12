package com.bamboo.system.service;

import com.bamboo.system.condition.SelfRoleCondition;
import com.bamboo.system.domain.SelfRole;
import org.springframework.data.domain.Page;

import java.util.List;

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
    String insertRole(SelfRole role) throws Exception;

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    String updateRole(SelfRole role) throws Exception;

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    String deleteRole(Long roleId) throws Exception;

    /**
     * 分页查询角色
     *
     * @param condition
     * @return
     */
    Page<SelfRole> getRolePaging(SelfRoleCondition condition);

    /**
     * 查询所有角色信息
     * @return
     */
    List<SelfRole> findAll();
}
