package com.cybercom.vaadin.example.stock;

public class StockQuota {
	
	private String companyName;
	
	private double value;

	public StockQuota(String companyName, double value) {
		this.companyName = companyName;
		this.value = value;
	}

	public String getCompanyName() {
		return companyName;
	}

	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	

}
