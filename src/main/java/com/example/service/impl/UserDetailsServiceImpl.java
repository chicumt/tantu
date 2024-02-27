package com.example.service.impl;


import com.example.dao.UserDao;

import com.example.entity.LoginUser;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version V1.0
 * @ClassName: com.zty.service.impl.UserDetailsService.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-11-25 16:58
 * @Description: 用户信息认证自定义类
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=userDao.queryByAccount(username);
        if (null==user){
            throw new UsernameNotFoundException("用户名未找到或者密码错误！");
        }
        return new LoginUser(user);
    }


}
