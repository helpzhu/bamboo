package com.bamboo.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019/9/10 19:57
 * @since JDK1.8
 */
@ApiModel(value = "分页返回VO")
public class ResponsePagingVo extends ResponseVo {

    @ApiModelProperty("当前页数")
    private Integer pageNum;

    @ApiModelProperty("每页行数")
    private Integer pageSize;

    @ApiModelProperty("总页数")
    private Integer totalPages;

    @ApiModelProperty("总行数")
    private Long totalRows;

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

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Long totalRows) {
        this.totalRows = totalRows;
    }
}
