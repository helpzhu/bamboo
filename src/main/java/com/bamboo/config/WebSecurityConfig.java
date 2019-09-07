package com.bamboo.config;

import com.bamboo.security.filter.MyFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @data 2019/9/3 20:12
 * @since JDK1.8
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 无需权限校验直接放行的路径
     */
    private final String[] PATH_PASS = {
            "/v2/api-docs",
            "/swagger-ui.html/**",
            "/swagger-resources/**",
            "/webjars/**"
    };

    /**
     * Request层面的配置，对应XML Configuration中的<http>元素
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login_p").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .csrf().disable()
                .httpBasic();
        // 将自定义的过滤器配置在FilterSecurityInterceptor之前
        http.addFilterBefore(new MyFilter(), FilterSecurityInterceptor.class);
    }

    /**
     * Web层面的配置，一般用来配置无需权限校验的路径
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(PATH_PASS);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
