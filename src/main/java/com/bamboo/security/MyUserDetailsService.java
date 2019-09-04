package com.bamboo.security;

import com.bamboo.security.User.MyUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @data 2019/9/3 20:21
 * @since JDK1.8
 */
@Configuration
public class MyUserDetailsService implements UserDetailsService {

    private final static Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MyUserVo userVo = new MyUserVo();
        userVo.setName(username);
        userVo.setPassword(this.passwordEncoder.encode("admin"));

        logger.info("encode password：" + userVo.getPassword());

        return new User(username, userVo.getPassword(), true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
