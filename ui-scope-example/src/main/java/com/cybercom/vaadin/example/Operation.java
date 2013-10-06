package com.cybercom.vaadin.example;

import java.util.Date;

import org.joda.time.LocalDate;

public class Operation {
	
	private String title;
	
	private int amount;
	
	private Date date;
	
	
	public Operation(String title, int amount, LocalDate localDate) {
		this.title = title;
		this.amount = amount;
		this.date = localDate.toDate();
		
	}

	public String getTitle() {
		return title;
	}

	public int getAmount() {
		return amount;
	}

	public Date getDate() {
		return date;
	}

}