package com.example.demo.service;

import com.example.demo.DemoApplication;
import com.example.demo.domain.StockRequest;
import com.example.demo.domain.StockRequest.StockShortInfo;
import com.example.demo.domain.StockResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class StockServiceTest {

    @Autowired
    private StockService stockService;

    @Test
    public void getStockInfoTest() {
        StockResponse response = stockService.getStockInfo(getTestRequest());
        assertEquals((Double) 2684.2, response.getValue());
        assertEquals(1, response.getAllocations().size());
    }

    private StockRequest getTestRequest() {
        List<StockShortInfo> stocks = new ArrayList<>();
        stocks.add(StockShortInfo.builder()
                .symbol("AAPL")
                .volume(10.0)
                .build());
        stocks.add(StockShortInfo.builder()
                .symbol("MDSO")
                .volume(10.0)
                .build());
        return StockRequest.builder()
                .stocks(stocks)
                .build();
    }
}
