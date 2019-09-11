package com.bamboo.security.provider;

import com.bamboo.security.service.SelfUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author bamboo
 * @version 1.0
 * @desc 自定义登陆
 * @date 2019-09-08 08:52
 * @since JDK1.8
 */
@Component
public class SelfAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private SelfUserDetailsService selfUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 用户名
        String userName = (String) authentication.getPrincipal();
        // 密码
        String userPassword = (String) authentication.getCredentials();

        UserDetails userDetails = this.selfUserDetailsService.loadUserByUsername(userName);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if (bCryptPasswordEncoder.matches(userPassword, userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(userName, userPassword);
        } else {
            throw new BadCredentialsException("密码错误");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
