package com.bamboo.security.User;

import java.io.Serializable;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @data 2019/9/3 20:22
 * @since JDK1.8
 */
public class MyUserVo implements Serializable {

    private String name;

    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
