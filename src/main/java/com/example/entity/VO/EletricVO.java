package com.example.entity.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class EletricVO {
    private Integer provinceId;
    private Double total;
    private Double profit;
    List<DataChart> dataCharts;

}
