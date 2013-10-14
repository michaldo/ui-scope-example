package com.cybercom.vaadin.example;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cybercom.vaadin.example.account.AccountView;
import com.cybercom.vaadin.example.stock.StockView;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;


/**
 * The Class TopView demonstrates how one Spring View bean is embedded
 * in another one.
 * TopView is not injected in any other Spring bean. Only SimpleBankUI 
 * has reference to TopView. Therefore TopView could be prototype-scope bean,
 * but is ui-scope for homogeneity
 */
@Component
@Scope("ui")
public class TopView extends TabSheet{
	
	@Autowired
	private AccountView accountView;
	
	@Autowired
	private StockView stockView;

	/**
	 * It is important to build component in @PostConstruct instead of constructor,
	 * because in constructor injected bean is empty (Spring nursery school).
	 */
	@PostConstruct
	private void construct() {
		addTab(accountView, "Accounts");
		addTab(stockView, "Stock (updated each 4 seconds");
		VerticalLayout marketing = new VerticalLayout();
		marketing.addComponent(new Label("Vaadin. Web applications easy"));
		marketing.addComponent(new Label("Spring. Let's build a better Enterprise"));
		marketing.addComponent(new Label("UI Scope. Two powers joined"));
		
		addTab(marketing, "Marketing");
	}
	
	
	
	
	

}
