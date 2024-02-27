package com.example.controller;



import com.example.entity.User;

import com.example.entity.UserLogin;
import com.example.service.LoginService;
import com.example.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;
    //无验证码的登录
    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){

        System.out.println(1111);
        return loginService.login(user);
    }



    //有验证码的登录
//    @PostMapping("/user/login")
//    public ResponseResult login(@RequestBody UserLogin user){
//
//
//        return loginService.loginCaptcha(user);
//    }

    @RequestMapping("/user/loginOut")
    public ResponseResult loginOut(){
        return loginService.loginOut();
    }


}
