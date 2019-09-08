package com.bamboo.system.api;

import io.swagger.annotations.*;

import static com.bamboo.system.api.LoginControllerApi.TAG_NAME;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019-09-07 20:18
 * @since JDK1.8
 */
@Api(value = "登陆登出相关接口", tags = TAG_NAME)
@ApiResponses({
        @ApiResponse(code = 200, message = "请求成功"),
        @ApiResponse(code = 400, message = "请求参数错误"),
        @ApiResponse(code = 403, message = "无权限"),
        @ApiResponse(code = 500, message = "服务器内部错误")
})
public interface LoginControllerApi {

    String TAG_NAME = "login";

    @ApiOperation(value = "登陆", tags = {TAG_NAME}, notes = "登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String")
    })
    void login(String userName, String password) throws Exception;

    @ApiOperation(value = "登出", tags = {TAG_NAME}, notes = "登出")
    void logout();
}
