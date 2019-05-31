package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class StockData {

    private String sector;
    private Double latestPrice;
    @JsonIgnore
    private Double assetValue;
}
