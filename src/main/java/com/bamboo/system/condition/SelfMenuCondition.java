package com.bamboo.system.condition;

import com.bamboo.base.RequestPagingVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019/9/11 16:00
 * @since JDK1.8
 */
@ApiModel("菜单查询VO")
public class SelfMenuCondition extends RequestPagingVo {

    @ApiModelProperty("菜单名称")
    private String menuName;

    @ApiModelProperty("菜单地址")
    private String menuUrl;

    @ApiModelProperty("类型 menu:菜单  button:按钮")
    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
