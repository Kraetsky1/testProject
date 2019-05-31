package com.example.demo.endpoint;

import com.example.demo.domain.StockRequest;
import com.example.demo.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockEndpoint {

    private final StockService stockService;

    @PostMapping
    public Object getStockInfo(@RequestBody StockRequest request) {
        return stockService.getStockInfo(request);
    }
}
