package com.bamboo.system.controller;

import com.bamboo.annotation.WebApiController;
import com.bamboo.base.ResponsePagingVo;
import com.bamboo.base.ResponseVo;
import com.bamboo.constant.ApiResult;
import com.bamboo.constant.SelfConstant;
import com.bamboo.system.api.MenuControllerApi;
import com.bamboo.system.api.entity.MenuTreeVo;
import com.bamboo.system.api.entity.SelfMenuVo;
import com.bamboo.system.condition.SelfMenuCondition;
import com.bamboo.system.domain.SelfMenu;
import com.bamboo.system.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019/9/11 16:29
 * @since JDK1.8
 */
@WebApiController
@RequestMapping("/menu")
public class MenuController implements MenuControllerApi {

    @Autowired
    private MenuService menuService;

    @GetMapping("/getMenuTree")
    @Override
    public ResponseVo getMenuTree() {
        try {
            Map<Long, MenuTreeVo> map = this.menuService.getMenuTree();
            return ResponseVo.success(map);
        } catch (Exception e) {
            return ResponseVo.failed("查询菜单树数据出错");
        }
    }

    @PreAuthorize(("hasAuthority('/menu/getMenuPaging')"))
    @PostMapping("/getMenuPaging")
    @Override
    public ResponsePagingVo getMenuPaging(@RequestBody SelfMenuCondition condition) {
        ResponsePagingVo resultVo = new ResponsePagingVo();
        try {
            Page<SelfMenu> menuPage = this.menuService.getMenuPaging(condition);
            resultVo.setTotalPages(menuPage.getTotalPages());
            resultVo.setTotalRows(menuPage.getTotalElements());
            resultVo.setPageNum(menuPage.getNumber());
            resultVo.setPageSize(menuPage.getSize());
            resultVo.setData(menuPage.getContent());
            resultVo.setResult(SelfConstant.SUCCESS);
        } catch (Exception e) {
            resultVo.setMessage("查询出错");
            resultVo.setResult(SelfConstant.FAIL);
        }
        return resultVo;
    }

    @PreAuthorize("hasAuthority('/menu/insertMenu')")
    @PostMapping("/insertMenu")
    @Override
    public ResponseVo insertMenu(@RequestBody SelfMenuVo menuVo) {
        ResponseVo resultVo = new ResponseVo();
        try {
            SelfMenu menu = new SelfMenu();
            BeanUtils.copyProperties(menuVo, menu);
            String resultMsg = this.menuService.insertMenu(menu);
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

    @PreAuthorize("hasAuthority('/menu/updateMenu')")
    @PostMapping("/updateMenu")
    @Override
    public ResponseVo updateMenu(@RequestBody SelfMenuVo menuVo) {
        ResponseVo resultVo = new ResponseVo();
        try {
            SelfMenu menu = new SelfMenu();
            BeanUtils.copyProperties(menuVo, menu);
            String resultMsg = this.menuService.updateMenu(menu);
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

    @PreAuthorize("hasAuthority('/menu/deleteMenu')")
    @PostMapping("/deleteMenu")
    @Override
    public ResponseVo deleteMenu(Long menuId) {
        ResponseVo resultVo = new ResponseVo();
        try {
            String resultMsg = this.menuService.deleteMenu(menuId);
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
