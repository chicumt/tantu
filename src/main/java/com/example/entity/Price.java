package com.example.entity;

import java.io.Serializable;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * (Price)实体类
 *
 * @author makejava
 * @since 2024-02-26 14:00:25
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Price implements Serializable {
    private static final long serialVersionUID = 198822605183281709L;

    private Integer id;

    private String area;

    private Double price;



}

