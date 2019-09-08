package com.bamboo.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019-09-08 14:24
 * @since JDK1.8
 */
@ApiModel(value = "统一返回VO", description = "统一返回VO")
public class ResultVo<T> {

    @ApiModelProperty("结果说明")
    private String message;

    @ApiModelProperty("返回结果,success:成功 fail：失败")
    private String result;

    @ApiModelProperty("具体对象")
    private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
