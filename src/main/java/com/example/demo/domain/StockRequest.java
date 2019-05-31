package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockRequest {

    private List<StockShortInfo> stocks;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StockShortInfo {
        private String symbol;
        private Double volume;
    }
}
