package com.latitude.genoapay.codingchallenge.data;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * The StockRequest for Stock API
 * Validation has been implemented, spring-boot-starter-validation added to POM.
 */
public class StockRequest {

    @NotBlank(message = "Request ID is mandatory")
    private String requestId;

    @NotNull(message = "Start Date is mandatory, format is yyyy-mm-ddThh:mm:ss")
    private LocalDateTime startDateTime;

    @NotNull(message = "End Date is mandatory, format is yyyy-mm-ddThh:mm:ss")
    private LocalDateTime endDateTime;

    @NotNull(message = "Stock Prices array is mandatory")
    private int[] stockprices;

    /**
     * Constructor with all variables
     * @param requestId
     * @param startDateTime
     * @param endDateTime
     * @param stockprices
     */
    public StockRequest(String requestId, LocalDateTime startDateTime, LocalDateTime endDateTime, int[] stockprices) {
        this.requestId = requestId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.stockprices = stockprices;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public int[] getStockprices() {
        return stockprices;
    }

    public void setStockprices(int[] stockprices) {
        this.stockprices = stockprices;
    }

}
