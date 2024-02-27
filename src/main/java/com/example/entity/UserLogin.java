package com.example.entity;

import lombok.Data;

@Data
public class UserLogin {
    private String account;

    private String password;
    private String code;
    private String uuid;
}
