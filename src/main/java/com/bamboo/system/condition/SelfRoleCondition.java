package com.bamboo.system.condition;

import com.bamboo.base.RequestPagingVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019/9/11 15:59
 * @since JDK1.8
 */
@ApiModel("角色查询VO")
public class SelfRoleCondition extends RequestPagingVo {

    @ApiModelProperty("角色名称")
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
