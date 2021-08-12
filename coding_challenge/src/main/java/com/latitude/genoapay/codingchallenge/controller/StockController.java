package com.latitude.genoapay.codingchallenge.controller;

import com.latitude.genoapay.codingchallenge.data.StockRequest;
import com.latitude.genoapay.codingchallenge.data.StockResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/stock")
public class StockController {

	@GetMapping
	public StockResponse getMaxProfit(@RequestBody @Validated StockRequest stockRequest) {
		return calcMaxProfit(stockRequest);
	}

	public StockResponse calcMaxProfit(StockRequest stockRequest) {
		int[] prices = stockRequest.getStockprices();
		int buyValue;
		int effectiveBuyValue = 0;
		int maxProfit = 0;
		int sellValue = 0;
		if (prices != null && prices.length > 0) {

			// Initialise buyValue to first element.
			buyValue = prices[0];
			effectiveBuyValue = buyValue;
			int profit;

			// Iterate through the array.
			for (int price : prices) {
				if (price > buyValue) {
					// If there is a profit calculate it
					profit = price - buyValue;
					if (profit > maxProfit) {
						// Profit exceeds our existing maximum so take this price.
						maxProfit = profit;
						effectiveBuyValue = buyValue;
						sellValue = price;
					}
				} else if (price < buyValue) {
					buyValue = price;
				}
			}
		}

		return new StockResponse(stockRequest, LocalDateTime.now(), maxProfit, effectiveBuyValue, sellValue);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
			MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

}