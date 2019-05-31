package com.example.demo.domain;

import lombok.Data;
import java.util.List;

@Data
public class StockRequest {

    private List<StockShortInfo> stocks;

    @Data
    public static class StockShortInfo {
        private String symbol;
        private Long volume;
    }
}
