package com.cybercom.vaadin.example.stock;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.vaadin.ui.UI;

@Component
@Scope(value="ui", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class StockPresenter {
	
	@Autowired
	private StockModel stockModel;
	
	@Autowired
	private StockView stockView;
	
	@Autowired 
	private StockScheduler scheduler;
	
	private UI ui;

	public void fireStockUpdate() {
		/*
		 * fireStockUpdate is dedicated to be called outside UI and must be properly synchronized
		 */
		ui.access(new Runnable() {
			@Override
			public void run() {
				displayStock();
			}
		});
		
		/*
		 * with java 8 it can look much better:
		 * UI.getCurrent().access(() -> displayStock());
		 */
	}
	
	public void displayStock() {
		stockView.getBeanItemContainer().removeAllItems();
		stockView.getBeanItemContainer().addAll(stockModel.getStockQuotas());
		
	}
	
	@PostConstruct
	private void register() {
		ui = UI.getCurrent();
		scheduler.registerPresenter(this);
		
	}
	
	@PreDestroy
	private void deregister() {
		scheduler.removePresenter(this);
	}


}
