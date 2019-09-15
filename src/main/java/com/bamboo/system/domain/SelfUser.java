package com.bamboo.system.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019/9/4 14:54
 * @since JDK1.8
 */
@Entity
@Table(name = "self_user")
public class SelfUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "uuid")
    private String uuId;

    @Column(name = "user_name", length = 100)
    private String userName;

    @Column(name = "user_account",length = 50, unique = true)
    private String userAccount;

    @Column(name = "password")
    private String password;

    @Column(name = "dept_id")
    private Long deptId;

    @Column(name = "dept_name")
    private String deptName;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "email", length = 50)
    private String email;

    /**
     * 状态 normal：正常  cancel：作废
     */
    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "modify_time")
    private Date modifyTime;

    public SelfUser() {
    }

    public SelfUser(String uuId, String userName, String userAccount, String password, Long deptId, String deptName, String phoneNumber, String email, String status, Date createTime, Date modifyTime) {
        this.uuId = uuId;
        this.userName = userName;
        this.userAccount = userAccount;
        this.password = password;
        this.deptId = deptId;
        this.deptName = deptName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.status = status;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUuId() {
        return uuId;
    }

    public void setUuId(String uuId) {
        this.uuId = uuId;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
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
}
