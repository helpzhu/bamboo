package com.bamboo.system.controller;

import com.bamboo.annotation.WebApiController;
import com.bamboo.base.ResponsePagingVo;
import com.bamboo.base.ResponseVo;
import com.bamboo.constant.ApiResult;
import com.bamboo.constant.SelfConstant;
import com.bamboo.system.api.UserControllerApi;
import com.bamboo.system.api.entity.SelfUserVo;
import com.bamboo.system.condition.SelfUserCondition;
import com.bamboo.system.domain.SelfUser;
import com.bamboo.system.service.UserService;
import com.bamboo.utils.BambooUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019/9/4 15:08
 * @since JDK1.8
 */
@WebApiController
@RequestMapping("/user")
public class UserController implements UserControllerApi {

    @Autowired
    private UserService userService;

    @GetMapping("/getCurrentUserInfo")
    @Override
    public ResponseVo getCurrentUserInfo() {
        try {
            return ResponseVo.success(BambooUtil.getCurrentUser());
        } catch (Exception e) {
            return ResponseVo.failed("获取当前登陆用户信息出错");
        }
    }

    @GetMapping("/getAllUsers")
    @Override
    public ResponseVo<SelfUser> getAllUsers() {
        ResponseVo resultVo = new ResponseVo();
        try {
            List<SelfUser> user = this.userService.findAll();
            resultVo.setResult(SelfConstant.SUCCESS);
            resultVo.setData(user);
        } catch (Exception e) {
            resultVo.setResult(SelfConstant.FAIL);
            resultVo.setMessage("查询失败");
        }
        return resultVo;
    }

    @GetMapping("/getUserByUserAccount")
    @Override
    public ResponseVo<SelfUser> getUserByUserAccount(String userAccount) {
        ResponseVo resultVo = new ResponseVo();
        try {
            if (StringUtils.isNotBlank(userAccount)) {
                SelfUser user = this.userService.getUserByUserAccount(userAccount);
                resultVo.setResult(SelfConstant.SUCCESS);
                resultVo.setData(user);
            } else {
                resultVo.setResult(SelfConstant.FAIL);
                resultVo.setMessage("用户账号不能为空");
            }
        } catch (Exception e) {
            resultVo.setResult(SelfConstant.FAIL);
            resultVo.setMessage("查询失败");
        }
        return resultVo;
    }

    @PreAuthorize("hasAuthority('/user/getUserPaging')")
    @PostMapping("/getUserPaging")
    @Override
    public ResponsePagingVo getUserPaging(@RequestBody SelfUserCondition condition) {
        ResponsePagingVo resultVo = new ResponsePagingVo();
        try {
            Page<SelfUser> selfUserPage = this.userService.getUserPaging(condition);
            resultVo.setTotalPages(selfUserPage.getTotalPages());
            resultVo.setTotalRows(selfUserPage.getTotalElements());
            resultVo.setPageNum(selfUserPage.getNumber());
            resultVo.setPageSize(selfUserPage.getSize());
            resultVo.setResult(SelfConstant.SUCCESS);
            resultVo.setData(selfUserPage.getContent());
        } catch (Exception e) {
            resultVo.setMessage("查询出错");
            resultVo.setResult(SelfConstant.FAIL);
        }
        return resultVo;
    }

    @PreAuthorize("hasAuthority('/user/insertUser')")
    @PostMapping("/insertUser")
    @Override
    public ResponseVo insertUser(@RequestBody SelfUserVo userVo) {
        ResponseVo resultVo = new ResponseVo();
        try {
            SelfUser user = new SelfUser();
            BeanUtils.copyProperties(userVo, user);
            String resultMsg = this.userService.insertUser(user);
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

    @PreAuthorize("hasAuthority('/user/updateUser')")
    @PostMapping("/updateUser")
    @Override
    public ResponseVo updateUser(@RequestBody SelfUserVo userVo) {
        ResponseVo resultVo = new ResponseVo();
        try {
            SelfUser user = new SelfUser();
            BeanUtils.copyProperties(userVo, user);
            String resultMsg = this.userService.updateUser(user);
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

    @PreAuthorize("hasAuthority('/user/deleteUser')")
    @PostMapping("/deleteUser")
    @Override
    public ResponseVo deleteUser(Long userId) {
        ResponseVo resultVo = new ResponseVo();
        try {
            String resultMsg = this.userService.deleteUser(userId);
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
