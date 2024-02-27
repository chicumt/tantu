package com.example.entity;

import java.io.Serializable;
import lombok.Data;
/**
 * (File)实体类
 *
 * @author makejava
 * @since 2024-01-21 17:58:29
 */
@Data
public class File implements Serializable {
    private static final long serialVersionUID = -46629293685717126L;

    private Long fileId;

    private byte[] file;

    private String type;

    private String name;



}

