package com.example.entity.admin;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * (Eletric)实体类
 *
 * @author makejava
 * @since 2024-02-27 19:39:43
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Eletric implements Serializable {
    private static final long serialVersionUID = 964940129399899370L;

    private Integer id;

    private Double mount;

    private LocalDateTime time;

    private Long stationId;

    private Double profit;



}

