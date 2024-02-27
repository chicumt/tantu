package com.example.service;


import com.example.entity.User;

import com.example.entity.UserLogin;
import com.example.utils.ResponseResult;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult loginOut();

    ResponseResult loginCaptcha(UserLogin user);
}

