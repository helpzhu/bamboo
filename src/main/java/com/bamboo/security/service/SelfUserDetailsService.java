package com.bamboo.security.service;

import com.bamboo.security.User.SelfSecurityUserVo;
import com.bamboo.system.dao.PermissionRepository;
import com.bamboo.system.domain.SelfUser;
import com.bamboo.system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019/9/3 20:21
 * @since JDK1.8
 */
@Configuration
public class SelfUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(SelfUserDetailsService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        SelfUser userVo = this.userService.getUserByUserAccount(userName);
        if (userVo == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        List<String> permissionList = this.permissionRepository.findAllPermissionByUserAccount(userVo.getUserAccount());
        String permission = permissionList.stream().map(String::toString).collect(Collectors.joining(","));

        logger.info("当前登录用户：{}，所拥有权限：{}", userVo.getUserAccount(), permission);
        SelfSecurityUserVo selfSecurityUserVo = new SelfSecurityUserVo(userVo.getUserName(), userVo.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(permission));
        return selfSecurityUserVo;
    }

    public static void main(String[] args) {
        List<String> permissionList = new ArrayList<>();

        permissionList.add("/user/list");
        permissionList.add("/user/list1");
        permissionList.add("/user/list2");

        String permission = permissionList.stream().map(String::toString).collect(Collectors.joining(","));

        System.out.println(permission);
    }
}
