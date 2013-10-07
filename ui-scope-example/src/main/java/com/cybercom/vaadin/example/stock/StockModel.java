package com.cybercom.vaadin.example.stock;

import java.util.Arrays;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class StockModel {
	
	
	private StockQuota[] stockQuotas = { 
			new StockQuota("Plum", 100.5),
			new StockQuota("Amazonia", 50.7), 
			new StockQuota("Facewall", 45) };
	
	public List<StockQuota> getStockQuotas() {
		return Arrays.asList(stockQuotas);
	}
	
	/**
	 * Stock exchange never sleep
	 */
	@Scheduled(fixedDelay=4000) 
	public void stockUpdated() {
		for (StockQuota stockQuota : stockQuotas) {
			stockQuota.setValue(stockQuota.getValue() + Math.random() * 10 - 5 );
		}
	}

}
