package com.example.demo.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StockResponse {

    private Double value;
    private List<Allocation> allocations;

    @Data
    @Builder
    public static class Allocation {
        private String sector;
        private Double assetValue;
        private Double proportion;
    }

}
