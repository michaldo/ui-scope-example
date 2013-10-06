package com.cybercom.vaadin.example;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;

/**
 * Never define View bean as Spring Proxy. 
 * That means circular dependency between 2 views is not possible 
 */
@Component
@Scope(value = "ui")
public class AccountView extends HorizontalLayout {

	private Table accountTable;
	
	private Table operationTable;

	private BeanItemContainer<Account> accounts = new BeanItemContainer<Account>(Account.class);

	private BeanItemContainer<Operation> operations = new BeanItemContainer<Operation>(Operation.class);

	@Autowired
	private AccountPresenter presenter;
	
	/**
	 * @see TopView#construct()
	 */
	@PostConstruct
	private void construct() {
		accountTable = new Table("Accounts - click entry for details", accounts);
		accountTable.setImmediate(true);
		accountTable.setSelectable(true);
		addComponent(accountTable);

		accountTable.addValueChangeListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				presenter.accountClicked((Account)accountTable.getValue());
			}
		});
		
		operationTable = new Table("Operations", operations);
		addComponent(operationTable);

	}

	public void attach() {
		/*
		 * There is a problem where view initialization;
		 * 1. Presenter has logic to initialize View
		 * 2. But Presenter cannot be called in @PostConstruct (circular reference)
		 * 
		 * I decided to defer initialization to method attach()
		 */
		presenter.initAccounts();

		super.attach();
	}

	public BeanItemContainer<Account> getAccounts() {
		return accounts;
	}
	
	public BeanItemContainer<Operation> getOperations() {
		return operations;
	}
}
