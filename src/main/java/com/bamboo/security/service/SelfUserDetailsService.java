package com.bamboo.security;

import com.bamboo.security.User.SelfUserVo;
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

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        SelfUser userVo = this.userService.getUserByUserName(userName);

        if (userVo == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        logger.info("userName:{}, encode password:{}", userName, userVo.getPassword());
        return new SelfUserVo(userVo.getUserName(), userVo.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
