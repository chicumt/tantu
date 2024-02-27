package com.example.handler;

import com.alibaba.fastjson.JSON;

import com.example.utils.ResponseResult;
import com.example.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @version V1.0
 * @ClassName: com.zty.handler.AuthenticationEntryPointImpl.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-11-27 10:59
 * @Description: 自定义认证异常处理类
 */

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        ResponseResult result;

        if(authException.getMessage().equals("用户名或密码错误")){

            result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(),"用户名或密码错误");
        }
        else {
            result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(),"用户认证失败，请重新登陆！");
        }


        String jsonString = JSON.toJSONString(result);
        //处理异常
        WebUtils.renderString(response,jsonString);
    }
}
