package com.cybercom.vaadin.example.stock;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;

@Component
@Scope("ui")
public class StockView extends Table {
	
	@Autowired
	private StockPresenter presenter; 
	
	private BeanItemContainer<StockQuota> beanItemContainer = new BeanItemContainer<StockQuota>(StockQuota.class);
	
	@PostConstruct
	private void construct() {
		setContainerDataSource(beanItemContainer);
	}
	
	public BeanItemContainer<StockQuota> getBeanItemContainer() {
		return beanItemContainer;
	}

	@Override
	public void attach() {
		presenter.displayStock();
		super.attach();
	}
	

}
