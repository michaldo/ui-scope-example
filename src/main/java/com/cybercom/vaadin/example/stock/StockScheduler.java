package com.cybercom.vaadin.example.stock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * The Class StockScheduler. It is not typical for MVP that presenter is triggered by 
 * scheduler.
 */
@Component
public class StockScheduler {
	
	private List<StockPresenter> presenters = Collections.synchronizedList(new ArrayList<StockPresenter>());
	
	public void registerPresenter(StockPresenter presenter) {
		presenters.add(presenter);
	}
	
	public void removePresenter(StockPresenter presenter) {
		presenters.remove(presenter);
	}
	
	@Scheduled(fixedDelay=5000)
	private void updatePresenters() {
		for (StockPresenter presenter : presenters) {
			presenter.fireStockUpdate();
		}
	}


}
