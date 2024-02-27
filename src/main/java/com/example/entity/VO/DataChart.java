package com.example.entity.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataChart {
    private String timeGroup;
    private Double totalMount;
    private Double totalProfit;
}
