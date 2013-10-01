package com.cybercom.vaadin.example;

import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

public class Contact {

	String fname;
	
	String lname;
	
	String company;
	
	boolean passFilter(String filterValue) {
		return containsIgnoreCase(fname, filterValue) || containsIgnoreCase(lname, filterValue) 
		|| containsIgnoreCase(company, filterValue);
	}


}
