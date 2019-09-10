package com.bamboo.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019/9/10 20:05
 * @since JDK1.8
 */
@ApiModel("分页请求VO")
public abstract class RequestPagingVo implements Serializable {

    @ApiModelProperty("当前页数")
    private Integer pageNum;

    @ApiModelProperty("每页行数")
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
