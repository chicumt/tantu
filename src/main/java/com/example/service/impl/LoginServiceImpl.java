package com.example.service.impl;


import com.example.entity.LoginUser;
import com.example.entity.User;
import com.example.entity.UserLogin;
import com.example.properties.JwtProperties;
import com.example.service.LoginService;
import com.example.utils.BaseContext;
import com.example.utils.JwtUtil;
import com.example.utils.RedisCache;
import com.example.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @version V1.0
 * @ClassName: com.zty.service.impl.LoginServiceImpl.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-11-25 20:51
 * @Description:
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProperties jwtProperties;


    @Override
    public ResponseResult login(User user) {
        //调用AuthenticationManager authenticationManager进行用户认证  用户账号，用户密码
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getAccount(),user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        //如果认证没通过，给出提示
        if (Objects.isNull(authentication)){
            throw new RuntimeException("登录失败！");
        }
        //认证通过，使用userid生成jwt返回前端，并userid为key，用户信息为value存入redis
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();

        //这里jwt增强token
        String jwt = JwtUtil.createJWT(userId);
        Map<String, String> map = new HashMap<>();
        map.put(jwtProperties.getAdminTokenName(),jwt);

        //这里存进redis
        redisCache.setCacheObject("login:"+userId,loginUser.getPermissions());
        return new ResponseResult(200,"登录成功",map);
    }

    @Override
    public ResponseResult loginOut() {
        //获取SecurityContextHolder中的用户信息
        String userID= BaseContext.getCurrentId();

        redisCache.deleteObject("login:"+userID);
        return new ResponseResult(200,"注销成功");
    }

    @Override
    public ResponseResult loginCaptcha(UserLogin user) {
        //调用AuthenticationManager authenticationManager进行用户认证  用户账号，用户密码
        String code=redisCache.getCacheObject(user.getUuid());
        if(!code.equals(user.getCode())) return new ResponseResult(201,"验证码错误");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getAccount(),user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        //如果认证没通过，给出提示
        if (Objects.isNull(authentication)){
            throw new RuntimeException("登录失败！");
        }
        //认证通过，使用userid生成jwt返回前端，并userid为key，用户信息为value存入redis
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();

        //这里jwt增强token
        String jwt = JwtUtil.createJWT(userId);
        Map<String, String> map = new HashMap<>();
        map.put(jwtProperties.getAdminTokenName(),jwt);

        //这里存进redis
        redisCache.setCacheObject("login:"+userId,loginUser.getPermissions());
        return new ResponseResult(200,"登录成功",map);
    }

}
