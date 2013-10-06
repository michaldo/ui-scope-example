package com.cybercom.vaadin.example;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

/**
 * Model class is pure business object and has no reference to any Web component. 
 */
@Component
public class AccountModel {
	
	public List<Account> getAccounts() {
		return Arrays.asList(new Account("123321"), new Account("456789"));
	}
	
	public List<Operation> getOperations(Account account) {
		if (account.getNumber().equals("123321")) {
			return Arrays.asList(
					new Operation("Credit", 400, new LocalDate(2013, 3, 24)),
					new Operation("Electricity", -150, new LocalDate(2013, 3, 25)),
					new Operation("Rent", -120, new LocalDate(2013, 3, 25))
					);
			
		} else if (account.getNumber().equals("456789")) {
			return Arrays.asList(
					new Operation("Salary", 1000, new LocalDate(2013, 3, 20)),
					new Operation("Amazon", -120, new LocalDate(2013, 3, 21)),
					new Operation("Google Play", -40, new LocalDate(2013, 3, 22)),
					new Operation("Credit", -400, new LocalDate(2013, 3, 24))
					);
		} else {
			return Collections.emptyList();
		}

	}


}
