package com.bamboo.security.config;

import com.bamboo.security.filter.SelfLoginFilter;
import com.bamboo.security.handler.*;
import com.bamboo.security.service.SelfUserDetailsService;
import com.bamboo.security.session.SelfInvalidSessionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.InvalidSessionStrategy;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019/9/3 20:12
 * @since JDK1.8
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SelfLoginSuccessHandler selfLoginSuccessHandler;
    @Autowired
    private SelfLoginFailHandler selfLoginFailHandler;
    @Autowired
    private SelfLogoutHandler selfLogoutHandler;
    @Autowired
    private SelfLogoutSuccessHandler selfLogoutSuccessHandler;
//    @Autowired
    //private SelfAuthenticationProvider selfAuthenticationProvider;
    @Autowired
    private SelfUserDetailsService selfUserDetailsService;

    /**
     * 无需权限校验直接放行的路径
     */
    private final String[] PATH_PASS = {
            "/v2/api-docs",
            "/swagger-ui.html/**",
            "/swagger-resources/**",
            "/webjars/**",
            "/h2-console/**"
    };

    /**
     * Request层面的配置，对应XML Configuration中的<http>元素
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭csrf
        http.csrf().disable()
                //关闭Session
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //.and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .and()
                //开放api路径
                .authorizeRequests().antMatchers("/api/**").permitAll()
                .anyRequest().authenticated()
                //开启自动配置的登陆功能
                .and()
                //自定义登录请求路径(post请求)
                .formLogin()//.usernameParameter("userName").passwordParameter("password")
                .loginProcessingUrl("/bamboo/login")
                //验证成功处理器
                .successHandler(selfLoginSuccessHandler)
                //验证失败处理器
                .failureHandler(selfLoginFailHandler).permitAll()
                //开启自动配置的注销功能
                .and()
                .logout()
                .logoutUrl("/bamboo/logout")
                .addLogoutHandler(selfLogoutHandler)
                //注销成功处理器
                .logoutSuccessHandler(selfLogoutSuccessHandler).permitAll()
            .and()
                .addFilterAfter(new SelfLoginFilter(), UsernamePasswordAuthenticationFilter.class);
                //.and()
                //添加token过滤器
                //.addFilter(new TokenAuthenticationFilter(authenticationManagerBean()));
        /*http.exceptionHandling().accessDeniedHandler(accessDeniedHandler()) // 权限不足处理器
                .and()
                //.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class) // 短信验证码校验
                //.addFilterBefore(imageCodeFilter, UsernamePasswordAuthenticationFilter.class) // 添加图形证码校验过滤器
                .formLogin() // 表单方式
                //.loginPage(febsSecurityProperties.getLoginUrl()) // 未认证跳转 URL
                .loginProcessingUrl("/login") // 处理登录认证 URL
                .successHandler(selfLoginSuccessHandler) // 处理登录成功
                .failureHandler(selfLoginFailHandler) // 处理登录失败
                //.and()
                //.rememberMe() // 添加记住我功能
                //.tokenRepository(persistentTokenRepository()) // 配置 token 持久化仓库
                //.tokenValiditySeconds(febsSecurityProperties.getRememberMeTimeout()) // rememberMe 过期时间，单为秒
                //.userDetailsService(febsUserDetailService) // 处理自动登录逻辑
                .and()
                .sessionManagement() // 配置 session管理器
                .invalidSessionStrategy(invalidSessionStrategy()) // 处理 session失效
                .maximumSessions(1) // 最大并发登录数量
                .expiredSessionStrategy(new SelfExpiredSessionStrategy()) // 处理并发登录被踢出
                .sessionRegistry(sessionRegistry()) // 配置 session注册中心
                .and()
                .and()
                .logout() // 配置登出
                .addLogoutHandler(selfLogoutHandler) // 配置登出处理器
                .logoutSuccessHandler(selfLogoutSuccessHandler)
                .logoutUrl("/logout") // 处理登出 url
                //.logoutSuccessUrl("/") // 登出后跳转到 /
                .deleteCookies("JSESSIONID") // 删除 JSESSIONID
                .permitAll()
                .and()
                .authorizeRequests() // 授权配置
                .antMatchers("/api/**").permitAll() // 免认证静态资源路径
                .antMatchers(
                        "/login",
                        "/logout"
//                        febsSecurityProperties.getLoginUrl(), // 登录路径
//                        FebsConstant.FEBS_REGIST_URL, // 用户注册 url
//                        febsSecurityProperties.getCode().getImage().getCreateUrl(), // 创建图片验证码路径
//                        febsSecurityProperties.getCode().getSms().getCreateUrl(), // 创建短信验证码路径
//                        febsSecurityProperties.getSocial().getSocialRedirectUrl(), // 重定向到社交账号注册（绑定）页面路径
//                        febsSecurityProperties.getSocial().getSocialBindUrl(), // 社交账号绑定 URL
//                        febsSecurityProperties.getSocial().getSocialRegistUrl() // 注册并绑定社交账号 URL
                ).permitAll() // 配置免认证路径
                .anyRequest()  // 所有请求
                .authenticated() // 都需要认证
                .and()
                .csrf().disable();
                //.apply(febsSmsCodeAuthenticationSecurityConfig) // 添加短信验证码认证流程
                //.and()
                //.apply(febsSocialSecurityConfig); // social 配置*/
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
        //auth.authenticationProvider(selfAuthenticationProvider);
        auth.userDetailsService(selfUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public InvalidSessionStrategy invalidSessionStrategy(){
        return new SelfInvalidSessionStrategy();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new SelfAuthenticationAccessDeniedHandler();
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
