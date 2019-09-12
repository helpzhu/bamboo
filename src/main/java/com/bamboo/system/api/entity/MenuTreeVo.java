package com.bamboo.system.api.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019/9/12 15:53
 * @since JDK1.8
 */
@ApiModel("菜单树VO")
public class MenuTreeVo extends SelfMenuVo {

    @ApiModelProperty("子菜单VO")
    private List<SelfMenuVo> selfMenuVoList;

    public List<SelfMenuVo> getSelfMenuVoList() {
        return selfMenuVoList;
    }

    public void setSelfMenuVoList(List<SelfMenuVo> selfMenuVoList) {
        this.selfMenuVoList = selfMenuVoList;
    }
}
