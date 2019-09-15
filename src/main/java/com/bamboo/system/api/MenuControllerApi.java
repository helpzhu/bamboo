package com.bamboo.system.api;

import com.bamboo.base.ResponsePagingVo;
import com.bamboo.base.ResponseVo;
import com.bamboo.system.api.entity.SelfMenuVo;
import com.bamboo.system.condition.SelfMenuCondition;
import io.swagger.annotations.*;

import static com.bamboo.system.api.MenuControllerApi.TAG_NAME;

/**
 * @author XuZhu
 * @version 1.0
 * @desc
 * @date 2019/9/11 16:25
 * @since JDK1.8
 */
@Api(description = "菜单相关接口", tags = TAG_NAME)
@ApiResponses({
        @ApiResponse(code = 200, message = "请求成功"),
        @ApiResponse(code = 400, message = "请求参数错误"),
        @ApiResponse(code = 403, message = "无权限"),
        @ApiResponse(code = 500, message = "服务器内部错误")
})
public interface MenuControllerApi {
    String TAG_NAME = "menu";

    @ApiOperation(value = "获取所有菜单信息", notes = "user", tags = TAG_NAME)
    ResponsePagingVo getMenuPaging(@ApiParam(value = "查询VO") SelfMenuCondition condition);

    @ApiOperation(value = "添加菜单信息", notes = "user", tags = TAG_NAME)
    ResponseVo insertMenu(@ApiParam(value = "菜单信息", required = true) SelfMenuVo menuVo);

    @ApiOperation(value = "修改菜单信息", notes = "user", tags = TAG_NAME)
    ResponseVo updateMenu(@ApiParam(value = "菜单信息", required = true) SelfMenuVo menuVo);

    @ApiOperation(value = "删除菜单信息", notes = "user", tags = TAG_NAME)
    ResponseVo deleteMenu(@ApiParam(value = "菜单id", required = true) Long menuId);

    @ApiOperation(value = "获取所有菜单信息", notes = "user", tags = TAG_NAME)
    ResponseVo getMenuTree();
}
