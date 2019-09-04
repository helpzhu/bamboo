package com.bamboo.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @data 2019/9/3 20:12
 * @since JDK1.8
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
