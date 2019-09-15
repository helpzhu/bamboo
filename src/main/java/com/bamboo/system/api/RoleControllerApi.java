package com.bamboo.system.api;

import com.bamboo.base.ResponsePagingVo;
import com.bamboo.base.ResponseVo;
import com.bamboo.system.api.entity.SelfRoleVo;
import com.bamboo.system.condition.SelfRoleCondition;
import io.swagger.annotations.*;

import static com.bamboo.system.api.RoleControllerApi.TAG_NAME;

/**
 * @author XuZhu
 * @version 1.0
 * @desc
 * @date 2019/9/11 16:25
 * @since JDK1.8
 */
@Api(description = "角色相关接口", tags = TAG_NAME)
@ApiResponses({
        @ApiResponse(code = 200, message = "请求成功"),
        @ApiResponse(code = 400, message = "请求参数错误"),
        @ApiResponse(code = 403, message = "无权限"),
        @ApiResponse(code = 500, message = "服务器内部错误")
})
public interface RoleControllerApi {
    String TAG_NAME = "role";

    @ApiOperation(value = "获取所有角色信息", notes = "user", tags = TAG_NAME)
    ResponsePagingVo getRolePaging(@ApiParam(value = "查询VO") SelfRoleCondition condition);

    @ApiOperation(value = "添加角色信息", notes = "user", tags = TAG_NAME)
    ResponseVo insertRole(@ApiParam(value = "角色信息", required = true) SelfRoleVo roleVo);

    @ApiOperation(value = "修改角色信息", notes = "user", tags = TAG_NAME)
    ResponseVo updateRole(@ApiParam(value = "角色信息", required = true) SelfRoleVo roleVo);

    @ApiOperation(value = "删除角色信息", notes = "user", tags = TAG_NAME)
    ResponseVo deleteRole(@ApiParam(value = "角色id", required = true) Long roleId);
}
