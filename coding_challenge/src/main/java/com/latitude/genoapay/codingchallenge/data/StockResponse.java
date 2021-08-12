package com.latitude.genoapay.codingchallenge.data;

import java.time.LocalDateTime;

/**
 * The StockResponse for Stock API
 */
public class StockResponse {
    private StockRequest stockRequest;
    private LocalDateTime processedDateTime;
    private Integer maxProfit;
    private Integer buyValue;
    private Integer sellValue;

    public StockResponse(StockRequest stockRequest, LocalDateTime processedDateTime, Integer maxProfit, Integer buyValue, Integer sellValue) {
        this.stockRequest = stockRequest;
        this.processedDateTime = processedDateTime;
        this.maxProfit = maxProfit;
        this.buyValue = buyValue;
        this.sellValue = sellValue;
    }

    public StockRequest getStockRequest() {
        return stockRequest;
    }

    public void setStockRequest(StockRequest stockRequest) {
        this.stockRequest = stockRequest;
    }

    public LocalDateTime getProcessedDateTime() {
        return processedDateTime;
    }

    public void setProcessedDateTime(LocalDateTime processedDateTime) {
        this.processedDateTime = processedDateTime;
    }

    public Integer getMaxProfit() {
        return maxProfit;
    }

    public void setMaxProfit(Integer maxProfit) {
        this.maxProfit = maxProfit;
    }

    public Integer getBuyValue() {
        return buyValue;
    }

    public void setBuyValue(Integer buyValue) {
        this.buyValue = buyValue;
    }

    public Integer getSellValue() {
        return sellValue;
    }

    public void setSellValue(Integer sellValue) {
        this.sellValue = sellValue;
    }
}
