package com.bamboo.system.controller;

import com.bamboo.annotation.WebApiController;
import com.bamboo.base.ResultVo;
import com.bamboo.constant.SelfConstant;
import com.bamboo.system.api.UserControllerApi;
import com.bamboo.system.entity.User;
import com.bamboo.system.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResultVo<User> getUserByUserName(String userName) {
        ResultVo resultVo = new ResultVo();
        try {
            if (StringUtils.isNotBlank(userName)) {
                User user = this.userService.getUserByUserName(userName);
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

    @GetMapping("/getAllUser")
    public ResultVo<List<User>> getAllUser() {
        ResultVo resultVo = new ResultVo();
        try {
            List<User> userList = this.userService.getAllUser();
            resultVo.setResult(SelfConstant.SUCCESS);
            resultVo.setData(userList);
        } catch (Exception e) {
            resultVo.setMessage("查询出错");
            resultVo.setResult(SelfConstant.FAIL);
        }
        return resultVo;
    }

    @PostMapping("/insertUser")
    @Override
    public ResultVo insertUser(@RequestBody User user) {
        ResultVo resultVo = new ResultVo();
        try {

        } catch (Exception e) {
            resultVo.setResult(SelfConstant.FAIL);
            resultVo.setMessage("添加失败");
        }
        return resultVo;
    }

    @PostMapping("/updateUser")
    @Override
    public ResultVo updateUser(@RequestBody User user) {
        ResultVo resultVo = new ResultVo();
        try {

        } catch (Exception e) {
            resultVo.setResult(SelfConstant.FAIL);
            resultVo.setMessage("修改失败");
        }
        return resultVo;
    }

    @PostMapping("/deleteUser")
    @Override
    public ResultVo deleteUser(Integer userId) {
        ResultVo resultVo = new ResultVo();
        try {

        } catch (Exception e) {
            resultVo.setResult(SelfConstant.FAIL);
            resultVo.setMessage("删除失败");
        }
        return resultVo;
    }
}
