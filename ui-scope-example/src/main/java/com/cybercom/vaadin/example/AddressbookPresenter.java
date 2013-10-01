package com.cybercom.vaadin.example;

import com.vaadin.data.util.BeanItemContainer;

public class AddressbookPresenter {

	private AddressbookModel model;
	
	public void initPersonContainer(BeanItemContainer<Contact> container) {
		container.addAll(model.getPersons());
	}

}
