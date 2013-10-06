package com.cybercom.vaadin.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * There is a circular reference between View and Presenter. It is typical
 * for MVP, but Spring guys don't like it. Only singleton beans may have circular reference.
 * Then, one side of relation must be proxy. View cannot be proxy because Vaadin is really confused.
 * Simply, Presenter must be the proxy.
 * 
 */
@Component
@Scope(value="ui", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class AccountPresenter {
	
	@Autowired
	private AccountView view;
	
	@Autowired
	private AccountModel model;
	

	public void initAccounts() {
		List<Account> accounts = model.getAccounts();
		view.getAccounts().addAll(accounts);
	}


	public void accountClicked(Account account) {
		if (account == null) {
			view.getOperations().removeAllItems();
			return;
		}
		List<Operation> operations = model.getOperations(account);
		view.getOperations().removeAllItems();
		view.getOperations().addAll(operations);
	}

}
