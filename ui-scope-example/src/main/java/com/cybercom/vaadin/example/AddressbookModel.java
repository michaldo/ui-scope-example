package com.cybercom.vaadin.example;

import java.util.ArrayList;
import java.util.List;

/*
 * Generate some in-memory example data to play with. In a real application
 * we could be using SQLContainer, JPAContainer or some other to persist the
 * data.
 */
public class AddressbookModel {


	public List<Contact> getPersons() {
		List<Contact> result = new ArrayList<Contact>();
	
	
		/* Create dummy data by randomly combining first and last names */
		String[] fnames = { "Peter", "Alice", "Joshua", "Mike", "Olivia",
				"Nina", "Alex", "Rita", "Dan", "Umberto", "Henrik", "Rene",
				"Lisa", "Marge" };
		String[] lnames = { "Smith", "Gordon", "Simpson", "Brown", "Clavel",
				"Simons", "Verne", "Scott", "Allison", "Gates", "Rowling",
				"Barks", "Ross", "Schneider", "Tate" };
		for (int i = 0; i < 1000; i++) {
			Contact person = new Contact();
			person.fname = fnames[(int) (fnames.length * Math.random())];
			person.lname = lnames[(int) (lnames.length * Math.random())];
			result.add(person);
		}

		return result;
	}

}
