package com.example.entity;

import java.io.Serializable;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2024-02-26 12:40:35
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {
    private static final long serialVersionUID = 249303346109804376L;

    private Long id;

    private String account;

    private String password;

    private String role;

    private String sign;
    private String username;

    private Long avatar;

    public User(Long a, String role){
        id=a;
        this.role=role;
    }

}

