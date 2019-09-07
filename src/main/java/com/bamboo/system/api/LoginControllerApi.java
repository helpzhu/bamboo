package com.bamboo.system.api;

import io.swagger.annotations.*;

@Api(value = "login", tags = "login")
@ApiResponses({
        @ApiResponse(code = 200, message = "请求成功"),
        @ApiResponse(code = 400, message = "请求参数错误"),
        @ApiResponse(code = 403, message = "无权限"),
        @ApiResponse(code = 500, message = "服务器内部错误")
})
public interface LoginControllerApi {

    @ApiOperation(value = "登陆", tags = {"login"}, notes = "登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", dataType = "String"),
            @ApiImplicitParam(name = "password", dataType = "String")
    })
    void login( String userName, String password);
}
