package com.bamboo.system.api.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019/9/9 20:07
 * @since JDK1.8
 */
@ApiModel("菜单VO")
public class SelfMenuVo implements Serializable {

    @ApiModelProperty("菜单id")
    private Long menuId;

    @ApiModelProperty("菜单名称")
    private String menuName;

    @ApiModelProperty("菜单URL")
    private String menuUrl;

    @ApiModelProperty("菜单图标")
    private String menuIcon;

    @ApiModelProperty("父级菜单id")
    private Long parentId;

    @ApiModelProperty("类型 menu:菜单  button:按钮")
    private String type;

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
