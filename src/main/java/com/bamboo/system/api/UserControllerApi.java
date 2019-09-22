package com.bamboo.system.api;

import com.bamboo.base.ResponsePagingVo;
import com.bamboo.base.ResponseVo;
import com.bamboo.system.api.entity.SelfUserVo;
import com.bamboo.system.condition.SelfUserCondition;
import com.bamboo.system.domain.SelfUser;
import io.swagger.annotations.*;

import static com.bamboo.system.api.UserControllerApi.TAG_NAME;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019/9/5 9:41
 * @since JDK1.8
 */
@Api(description = "用户相关接口", tags = TAG_NAME)
@ApiResponses({
        @ApiResponse(code = 200, message = "请求成功"),
        @ApiResponse(code = 400, message = "请求参数错误"),
        @ApiResponse(code = 403, message = "无权限"),
        @ApiResponse(code = 500, message = "服务器内部错误")
})
public interface UserControllerApi {

    String TAG_NAME = "user";

    @ApiOperation(value = "获取所有用户信息", tags = TAG_NAME)
    ResponseVo<SelfUser> getAllUsers();

    @ApiOperation(value = "根据用户账号获取用户信息用户信息", tags = TAG_NAME)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userAccount", value = "用户账号", dataType = "String", required = true)
    })
    ResponseVo<SelfUser> getUserByUserAccount(String userAccount);

    @ApiOperation(value = "获取当前登陆用户信息", tags = TAG_NAME)
    ResponseVo getCurrentUserInfo();

    @ApiOperation(value = "获取所有用户信息", notes = "user", tags = TAG_NAME)
    ResponsePagingVo getUserPaging(@ApiParam(value = "查询VO") SelfUserCondition condition);

    @ApiOperation(value = "添加用户信息", notes = "user", tags = TAG_NAME)
    ResponseVo insertUser(@ApiParam(value = "用户信息", required = true) SelfUserVo userVo);

    @ApiOperation(value = "修改用户信息", notes = "user", tags = TAG_NAME)
    ResponseVo updateUser(@ApiParam(value = "用户信息", required = true) SelfUserVo userVo);

    @ApiOperation(value = "删除用户信息", notes = "user", tags = TAG_NAME)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = "Long", required = true)
    })
    ResponseVo deleteUser(Long userId);
}
