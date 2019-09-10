package com.bamboo.system.condition;

import com.bamboo.base.RequestPagingVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019/9/10 20:13
 * @since JDK1.8
 */
@ApiModel("用户查询条件")
public class SelfUserCondition extends RequestPagingVo {

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("用户账号")
    private String userAccount;

    @ApiModelProperty("部门名称")
    private String deptName;

    @ApiModelProperty("手机号")
    private String phoneNumber;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("状态 normal：正常  cancel：作废")
    private String status;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date modifyTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(SelfUserCondition.class);
    }
}
