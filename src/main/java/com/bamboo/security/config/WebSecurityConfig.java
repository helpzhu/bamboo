package com.bamboo.security.config;

import com.bamboo.security.handler.SelfLoginFailHandler;
import com.bamboo.security.handler.SelfLoginSuccessHandler;
import com.bamboo.security.handler.SelfLogoutSuccessHandler;
import com.bamboo.security.provider.SelfAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019/9/3 20:12
 * @since JDK1.8
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SelfLoginSuccessHandler selfLoginSuccessHandler;
    @Autowired
    private SelfLoginFailHandler selfLoginFailHandler;
    @Autowired
    private SelfLogoutSuccessHandler selfLogoutSuccessHandler;
    @Autowired
    private SelfAuthenticationProvider selfAuthenticationProvider;

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
        //关闭csrf
        http.csrf().disable()
                //关闭Session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //开放api路径
                .authorizeRequests().antMatchers("/api/**").
                permitAll()
                .anyRequest().authenticated()
                //开启自动配置的登陆功能
                .and()
                //自定义登录请求路径(post请求)
                .formLogin().usernameParameter("userName").passwordParameter("password")
                .loginProcessingUrl("/bamboo/login")
                //验证成功处理器
                .successHandler(selfLoginSuccessHandler)
                //验证失败处理器
                .failureHandler(selfLoginFailHandler).permitAll()
                .and()
                //关闭拦截未登录自动跳转,改为返回json信息
                .exceptionHandling().authenticationEntryPoint(selfLoginUrlAuthenticationEntryPoint())
                //开启自动配置的注销功能
                .and()
                .logout()
                .logoutUrl("/bamboo/logout")
                //注销成功处理器
                .logoutSuccessHandler(selfLogoutSuccessHandler).permitAll();
                //.and()
                //添加token过滤器
                //.addFilter(new TokenAuthenticationFilter(authenticationManagerBean()));
    }

    /**
     * Web层面的配置，一般用来配置无需权限校验的路径
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(PATH_PASS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(selfAuthenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 身份认证失败处理类
     * @return AuthenticationEntryPoint
     */
    @Bean
    public AuthenticationEntryPoint selfLoginUrlAuthenticationEntryPoint() {
        System.out.println("身份认证失败");
        return null;//new SelfLoginUrlAuthenticationEntryPoint("/");
    }

    /**
     * 重写方法，是上下文可以获取本地缓存对象
     * @return AuthenticationManager  本地缓存对象
     * @throws Exception 异常
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 初始化权限相关数据
     * @return
     */
    @Bean
    public PermissionInitializing permissionInitializing() {
        return new PermissionInitializing();
    }
}
