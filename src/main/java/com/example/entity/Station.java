package com.example.entity;

import java.io.Serializable;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * (Station)实体类
 *
 * @author makejava
 * @since 2024-02-26 13:09:06
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Station implements Serializable {
    private static final long serialVersionUID = 481652699805462373L;

    private Long id;

    private Long userId;

    private String area;

    private String detailArea;
    /**
     * 0停用，1正常
     */
    private Integer status;
    /**
     * 0申请中，1通过
     */
    private Integer apply;

    private Integer provinceId;
    private Integer pieces;
    private Double total;
    private Double profit;



}

