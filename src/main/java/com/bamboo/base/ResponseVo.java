package com.bamboo.base;

import com.bamboo.constant.ApiResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019-09-08 14:24
 * @since JDK1.8
 */
@ApiModel(value = "统一返回VO")
public class ResponseVo<T> {

    @ApiModelProperty("结果说明")
    private String message;

    @ApiModelProperty("返回结果,success:成功 fail：失败")
    private String result;

    @ApiModelProperty("具体对象")
    private T data;

    public static <T> ResponseVo success(T t) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setResult(ApiResult.SUCCESS);
        responseVo.setData(t);
        return responseVo;
    }

    public static ResponseVo success(String message) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setResult(ApiResult.SUCCESS);
        responseVo.setMessage(message);
        return responseVo;
    }

    public static ResponseVo failed(String message) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setResult(ApiResult.FAILED);
        responseVo.setMessage(message);
        return responseVo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(ApiResult apiResult) {
        this.result = apiResult.value();
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
