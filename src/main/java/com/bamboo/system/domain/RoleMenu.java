package com.bamboo.system.domain;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019/9/9 20:26
 * @since JDK1.8
 */
@Table(name = "role_menu")
public class RoleMenu implements Serializable {

    @Column(name = "role_id", nullable = false)
    private Long roleId;

    @Column(name = "menu_id", nullable = false)
    private Long menuId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
