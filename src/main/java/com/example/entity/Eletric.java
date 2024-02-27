package com.example.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * (Eletric)实体类
 *
 * @author makejava
 * @since 2024-02-26 13:18:54
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Eletric implements Serializable {
    private static final long serialVersionUID = 791291898878713902L;

    private Integer id;

    private Double mount;

    private LocalDateTime time;

    private Long stationId;
    private Double profit;



}

