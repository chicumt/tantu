package com.example.config;



import com.example.filter.JwtAuthenticationTokenFilter;
import com.example.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
             //关闭csrf
             .csrf().disable()
             //不通过Session获取SecurityContext
             .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
             .and()
             .authorizeRequests()
             // 对于登录接口 允许匿名访问
             .antMatchers("/user/login").anonymous()
                .antMatchers("/captcha").anonymous()
             //.antMatchers("/user/updateUser").anonymous()
             //使用配置配置接口权限。  hasAuthority("1") 数据库中校验的字段 -》  m.`perms`   自己去看sql就知道了
             .antMatchers("/test2").hasAnyAuthority("vip")
                .antMatchers("/ws/**").hasAnyAuthority("user")
                .antMatchers("/file/**").permitAll()
                .antMatchers("/video/**").permitAll()
                .antMatchers("/admin/**").permitAll()
             // 除上面外的所有请求全部需要鉴权认证
             .anyRequest().authenticated();
        //配置自定义jwt过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //配置自定义异常处理
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                                .accessDeniedHandler(accessDeniedHandler);

        //SpringSecurity允许跨域
        http.cors();
    }
//    @Override
//    public void configure(WebSecurity webSecurity){
//        webSecurity.ignoring().antMatchers(
//                "/ws/**"
//        );
//    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
