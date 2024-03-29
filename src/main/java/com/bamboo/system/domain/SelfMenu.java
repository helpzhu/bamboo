package com.bamboo.system.domain;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019/9/9 20:07
 * @since JDK1.8
 */
@Entity
@Table(name = "self_menu")
public class SelfMenu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "menu_id")
    private Long menuId;

    @Column(name = "menu_name", nullable = false, unique = true)
    private String menuName;

    @Column(name = "menu_url")
    private String menuUrl;

    @Column(name = "permission")
    private String permission;

    @Column(name = "menu_icon")
    private String menuIcon;

    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 类型 menu:菜单  button:按钮
     */
    @Column(name = "type", nullable = false, length = 20)
    private String type;

    public SelfMenu() {
    }

    public SelfMenu(String menuName, String menuUrl, String permission, String menuIcon, Long parentId, String type) {
        this.menuName = menuName;
        this.permission = permission;
        this.menuUrl = menuUrl;
        this.menuIcon = menuIcon;
        this.parentId = parentId;
        this.type = type;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
