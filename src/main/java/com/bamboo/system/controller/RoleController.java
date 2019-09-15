package com.bamboo.system.controller;

import com.bamboo.annotation.WebApiController;
import com.bamboo.base.ResponsePagingVo;
import com.bamboo.base.ResponseVo;
import com.bamboo.constant.ApiResult;
import com.bamboo.constant.SelfConstant;
import com.bamboo.system.api.RoleControllerApi;
import com.bamboo.system.api.entity.SelfRoleVo;
import com.bamboo.system.condition.SelfRoleCondition;
import com.bamboo.system.domain.SelfRole;
import com.bamboo.system.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019/9/11 16:29
 * @since JDK1.8
 */
@WebApiController
@RequestMapping("/role")
public class RoleController implements RoleControllerApi {

    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasAuthority('/role/getRolePaging')")
    @PostMapping("/getRolePaging")
    @Override
    public ResponsePagingVo getRolePaging(@RequestBody SelfRoleCondition condition) {
        ResponsePagingVo resultVo = new ResponsePagingVo();
        try {
            Page<SelfRole> rolePage = this.roleService.getRolePaging(condition);
            resultVo.setTotalPages(rolePage.getTotalPages());
            resultVo.setTotalRows(rolePage.getTotalElements());
            resultVo.setPageNum(rolePage.getNumber());
            resultVo.setPageSize(rolePage.getSize());
            resultVo.setData(rolePage.getContent());
            resultVo.setResult(SelfConstant.SUCCESS);
        } catch (Exception e) {
            resultVo.setMessage("查询出错");
            resultVo.setResult(SelfConstant.FAIL);
        }
        return resultVo;
    }

    @PreAuthorize("hasAuthority('/role/insertRole')")
    @PostMapping("/insertRole")
    @Override
    public ResponseVo insertRole(@RequestBody SelfRoleVo roleVo) {
        ResponseVo resultVo = new ResponseVo();
        try {
            SelfRole role = new SelfRole();
            BeanUtils.copyProperties(roleVo, role);
            String resultMsg = this.roleService.insertRole(role);
            if (StringUtils.equals(SelfConstant.SUCCESS, resultMsg)) {
                resultVo.setResult(ApiResult.SUCCESS);
            } else {
                resultVo.setMessage(resultMsg);
                resultVo.setResult(ApiResult.FAILED);
            }
        } catch (Exception e) {
            resultVo.setResult(SelfConstant.FAIL);
            resultVo.setMessage("添加失败");
        }
        return resultVo;
    }

    @PreAuthorize("hasAuthority('/role/updateRole')")
    @PostMapping("/updateRole")
    @Override
    public ResponseVo updateRole(@RequestBody SelfRoleVo roleVo) {
        ResponseVo resultVo = new ResponseVo();
        try {
            SelfRole role = new SelfRole();
            BeanUtils.copyProperties(roleVo, role);
            String resultMsg = this.roleService.updateRole(role);
            if (StringUtils.equals(SelfConstant.SUCCESS, resultMsg)) {
                resultVo.setResult(ApiResult.SUCCESS);
            } else {
                resultVo.setMessage(resultMsg);
                resultVo.setResult(ApiResult.FAILED);
            }
        } catch (Exception e) {
            resultVo.setResult(SelfConstant.FAIL);
            resultVo.setMessage("修改失败");
        }
        return resultVo;
    }

    @PreAuthorize("hasAuthority('/role/deleteRole')")
    @PostMapping("/deleteRole")
    @Override
    public ResponseVo deleteRole(Long roleId) {
        ResponseVo resultVo = new ResponseVo();
        try {
            String resultMsg = this.roleService.deleteRole(roleId);
            if (StringUtils.equals(SelfConstant.SUCCESS, resultMsg)) {
                resultVo.setResult(ApiResult.SUCCESS);
            } else {
                resultVo.setMessage(resultMsg);
                resultVo.setResult(ApiResult.FAILED);
            }
        } catch (Exception e) {
            resultVo.setResult(SelfConstant.FAIL);
            resultVo.setMessage("删除失败");
        }
        return resultVo;
    }
}
