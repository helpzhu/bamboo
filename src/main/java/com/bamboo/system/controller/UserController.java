package com.bamboo.system.controller;

import com.bamboo.annotation.WebApiController;
import com.bamboo.base.ResponsePagingVo;
import com.bamboo.base.ResponseVo;
import com.bamboo.constant.SelfConstant;
import com.bamboo.system.api.UserControllerApi;
import com.bamboo.system.condition.SelfUserCondition;
import com.bamboo.system.domain.SelfUser;
import com.bamboo.system.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/getUserByName")
    @Override
    public ResponseVo<SelfUser> getUserByUserName(String userName) {
        ResponseVo resultVo = new ResponseVo();
        try {
            if (StringUtils.isNotBlank(userName)) {
                SelfUser user = this.userService.getUserByUserName(userName);
                resultVo.setResult(SelfConstant.SUCCESS);
                resultVo.setData(user);
            } else {
                resultVo.setResult(SelfConstant.FAIL);
                resultVo.setMessage("用户名称不能为空");
            }
        } catch (Exception e) {
            resultVo.setResult(SelfConstant.FAIL);
            resultVo.setMessage("查询失败");
        }
        return resultVo;
    }

    @GetMapping("/getUserPaging")
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

    @PostMapping("/insertUser")
    @Override
    public ResponseVo insertUser(@RequestBody SelfUser user) {
        ResponseVo resultVo = new ResponseVo();
        try {

        } catch (Exception e) {
            resultVo.setResult(SelfConstant.FAIL);
            resultVo.setMessage("添加失败");
        }
        return resultVo;
    }

    @PostMapping("/updateUser")
    @Override
    public ResponseVo updateUser(@RequestBody SelfUser user) {
        ResponseVo resultVo = new ResponseVo();
        try {

        } catch (Exception e) {
            resultVo.setResult(SelfConstant.FAIL);
            resultVo.setMessage("修改失败");
        }
        return resultVo;
    }

    @PostMapping("/deleteUser")
    @Override
    public ResponseVo deleteUser(Integer userId) {
        ResponseVo resultVo = new ResponseVo();
        try {

        } catch (Exception e) {
            resultVo.setResult(SelfConstant.FAIL);
            resultVo.setMessage("删除失败");
        }
        return resultVo;
    }
}
