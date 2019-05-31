package com.example.demo.service;

import com.example.demo.domain.StockData;
import com.example.demo.domain.StockRequest;
import com.example.demo.domain.StockRequest.StockShortInfo;
import com.example.demo.domain.StockResponse;
import com.example.demo.domain.StockResponse.Allocation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class StockService {

    private final RestTemplate restTemplate;
    private static final String API_URL_STRING = "https://api.iextrading.com/1.0/stock/{symbol}/quote";

    public StockResponse getStockInfo(StockRequest request) {
        log.debug("Getting allocations information for a request: {}.", request);
        List<StockData> stocksData = fetchStocksFullData(request.getStocks());
        return StockResponse.builder()
                .value(countValue(stocksData))
                .allocations(countSectorInformation(stocksData))
                .build();
    }


    private List<StockData> fetchStocksFullData(List<StockShortInfo> stocks) {
        List<StockData> stocksData = new ArrayList<>();
        stocks.forEach(s -> {
            StockData stockData = restTemplate.getForObject(API_URL_STRING,
                    StockData.class, Collections.singletonMap("symbol", s.getSymbol()));
            stockData.setAssetValue(s.getVolume() * stockData.getLatestPrice());
            stocksData.add(stockData);
        });
        return stocksData;
    }

    private List<Allocation> countSectorInformation(List<StockData> stocksData) {
        Double value = countValue(stocksData);
        List<Allocation> allocations = new ArrayList<>();
        Map<String, Double> map = new HashMap();
        stocksData.forEach(sd -> map.merge(sd.getSector(), sd.getAssetValue(), (a, b) -> a + b));
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            allocations.add(Allocation.builder()
                    .sector(entry.getKey())
                    .assetValue(entry.getValue())
                    .proportion(entry.getValue() / value)
                    .build());
        }
        return allocations;
    }

    private Double countValue(List<StockData> stocksData) {
        Double value = 0.0;
        for (StockData sd : stocksData) {
            value += sd.getAssetValue();
        }
        return value;
    }
}
