package com.latitude.genoapay.codingchallenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.latitude.genoapay.codingchallenge.controller.StockController;
import com.latitude.genoapay.codingchallenge.data.StockRequest;
import com.latitude.genoapay.codingchallenge.data.StockResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class CodingChallengeApplicationTests {
	private StockController stockController = new StockController();
	private StockRequest stockRequest;

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	ObjectMapper mapper;

	@BeforeEach
	private void beforeTests() {
		stockRequest = new StockRequest("1", LocalDateTime.now(), LocalDateTime.now(), null);
	}

	@Test
	void contextLoads() {
		assertTrue(stockController != null);
	}

	@Test
	/**
	 * Empty prices array should return zero buy/sell/max profit
	 */
	void testEmptyPrices() throws Exception {
		stockRequest.setStockprices(new int[0]);
		StockResponse stockResponse = stockController.calcMaxProfit(stockRequest);
		assertEquals(0, stockResponse.getMaxProfit());
		assertEquals(0, stockResponse.getBuyValue());
		assertEquals(0, stockResponse.getSellValue());
	}

	@Test
	/**
	 * The Request must be saved in the response.
	 */
	void testRequestIsReturned() throws Exception {
		stockRequest.setStockprices(new int[0]);
		StockResponse stockResponse = stockController.calcMaxProfit(stockRequest);
		assertEquals(stockRequest, stockResponse.getStockRequest());
	}

	@Test
	/**
	 * Simple price lift; we should have the expected buy/sell/max profit
	 */
	void testSimplePriceLift() throws Exception {
		stockRequest.setStockprices(new int[]{1,5,7});
		StockResponse stockResponse = stockController.calcMaxProfit(stockRequest);
		assertEquals(6, stockResponse.getMaxProfit());
		assertEquals(1, stockResponse.getBuyValue());
		assertEquals(7, stockResponse.getSellValue());
	}

	@Test
	/**
	 * When price moves up and down we should still have the expected buy/sell/max profit
	 */
	void testUpsAndDowns() throws Exception {
		stockRequest.setStockprices(new int[]{1,5,7,2,4});
		StockResponse stockResponse = stockController.calcMaxProfit(stockRequest);
		assertEquals(6, stockResponse.getMaxProfit());
		assertEquals(1, stockResponse.getBuyValue());
		assertEquals(7, stockResponse.getSellValue());
	}

	@Test
	/**
	 * Price rallies twice - must have the max
	 */
	void testLateTopPrice() throws Exception {
		stockRequest.setStockprices(new int[]{2,1,7,2,4,9});
		StockResponse stockResponse = stockController.calcMaxProfit(stockRequest);
		assertEquals(8, stockResponse.getMaxProfit());
		assertEquals(1, stockResponse.getBuyValue());
		assertEquals(9, stockResponse.getSellValue());
	}

	@Test
	/**
	 * Web layer test.
	 * Prices array is not provided.  Must return status 400.
	 * Note: there is also a meaningful error message seen in Postman
	 */
	void testPricesNotProvided() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/stock")
				.content(this.mapper.writeValueAsString(stockRequest))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError())
				.andReturn();

		assertEquals(400, result.getResponse().getStatus());
	}

	@Test
	/**
	 * Web layer test.
	 * Request ID is not provided.  Must return status 400.
	 * Note: there is also a meaningful error message seen in Postman
	 */
	void testIdNotProvided() throws Exception {
		stockRequest.setRequestId(null);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/stock")
				.content(this.mapper.writeValueAsString(stockRequest))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError())
				.andReturn();

		assertEquals(400, result.getResponse().getStatus());
	}
}
