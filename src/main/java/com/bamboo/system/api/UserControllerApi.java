package com.bamboo.system.api;

import com.bamboo.base.ResultVo;
import com.bamboo.system.entity.User;
import io.swagger.annotations.*;

import java.util.List;

import static com.bamboo.system.api.UserControllerApi.TAG_NAME;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019/9/5 9:41
 * @since JDK1.8
 */
@Api(value = "用户相关接口", tags = TAG_NAME)
@ApiResponses({
        @ApiResponse(code = 200, message = "请求成功"),
        @ApiResponse(code = 400, message = "请求参数错误"),
        @ApiResponse(code = 403, message = "无权限"),
        @ApiResponse(code = 500, message = "服务器内部错误")
})
public interface UserControllerApi {

    String TAG_NAME = "user";

    @ApiOperation(value = "根据用户名获取用户信息用户信息", tags = TAG_NAME)
    ResultVo<User> getUserByUserName(@ApiParam(value = "用户名", required = true) String userName);

    @ApiOperation(value = "获取所有用户信息", notes = "user", tags = TAG_NAME)
    ResultVo<List<User>> getAllUser();

    @ApiOperation(value = "添加用户信息", notes = "user", tags = TAG_NAME)
    ResultVo insertUser(@ApiParam(value = "用户信息", required = true) User user);

    @ApiOperation(value = "修改用户信息", notes = "user", tags = TAG_NAME)
    ResultVo updateUser(@ApiParam(value = "用户信息", required = true) User user);

    @ApiOperation(value = "删除用户信息", notes = "user", tags = TAG_NAME)
    ResultVo deleteUser(@ApiParam(value = "用户id", required = true) Integer userId);
}
