package com.bamboo.system.api;

import com.bamboo.system.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @data 2019/9/5 9:41
 * @since JDK1.8
 */
@Api(value = "user", tags = {"user"})
@ApiResponses({
        @ApiResponse(code = 200, message = "请求成功"),
        @ApiResponse(code = 400, message = "请求参数错误"),
        @ApiResponse(code = 403, message = "无权限"),
        @ApiResponse(code = 500, message = "服务器内部错误")
})
public interface UserControllerApi {

    @ApiOperation(value = "getUser", notes = "user", tags = "user")
    List<User> getAllUser();

}
