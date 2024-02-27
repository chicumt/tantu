package com.example.filter;


import com.example.entity.LoginUser;
import com.example.entity.User;
import com.example.properties.JwtProperties;
import com.example.utils.BaseContext;
import com.example.utils.JwtUtil;
import com.example.utils.RedisCache;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @version V1.0
 * @ClassName: com.zty.filter.JwtAuthenticationTokenFilter.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-11-26 15:41
 * @Description: 统一接口token效验过滤器
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;
    @Autowired
    public JwtProperties jwtProperties;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        //1.获取token
        String token = request.getHeader(jwtProperties.getAdminTokenName());
        // 鉴定放行路径
        List<String> permitAllPaths = Arrays.asList("/video/**"); // Add your permitAll paths here
        AntPathMatcher pathMatcher = new AntPathMatcher();

        String path = request.getRequestURI();
        String contextPath = request.getContextPath();
// Create a new final variable for the modified path
        final String finalPath = path.substring(contextPath.length()); // This variable is effectively final

// Check if the request path is in the permitAll list
        boolean isPermitAllPath = permitAllPaths.stream().anyMatch(p -> pathMatcher.match(p, finalPath));
        if (!StringUtils.hasText(token)|| isPermitAllPath){
            //没有token 直接放行。后续spring自己拦截了
            filterChain.doFilter(request,response);
            return;
        }
        String userID;
        //2.解析token
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userID = claims.getSubject();

            BaseContext.setCurrentId(userID);
        } catch (Exception e) {
//            e.printStackTrace();
            throw new RuntimeException("token非法");
//            log.info("token非法");
        }
        //3.通过解析的userID去redis查询用户信息
        String permission = redisCache.getCacheObject("login:" + userID);
        //System.out.println(loginUser);
        if(Objects.isNull(permission)) {
            //redis中没有存 用户的值，抛出异常
            throw new RuntimeException("用户未登录");

        }
        //System.out.println("token ===============------------- " + token);
        //4.存入SecurityContextHolder供后续过滤器使用
        //5.存入权限信息
        LoginUser loginUser=new LoginUser(new User(Long.parseLong(userID),permission));
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //6.放行
        filterChain.doFilter(request,response);
    }
}
