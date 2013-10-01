package com.cybercom.vaadin.example;

import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class AddressbookView extends HorizontalSplitPanel { 
	
	private Table contactList = new Table();
	private TextField searchField = new TextField();
	private Button addNewContactButton = new Button("New");
	private Button removeContactButton = new Button("Remove this contact");
	private FormLayout editorLayout = new FormLayout();
	BeanFieldGroup<Contact> editorFields = new BeanFieldGroup<Contact>(Contact.class);
	
	private static final String FNAME = "First Name";
	private static final String LNAME = "Last Name";
	private static final String COMPANY = "Company";
	private static final String[] fieldNames = new String[] { FNAME, LNAME,
			COMPANY, "Mobile Phone", "Work Phone", "Home Phone", "Work Email",
			"Home Email", "Street", "City", "Zip", "State", "Country" };
	
	private AddressbookPresenter presenter;
	
	
	/*
	 * Any component can be bound to an external data source. This example uses
	 * just a dummy in-memory list, but there are many more practical
	 * implementations.
	 */
	BeanItemContainer<Contact> contactContainer = new BeanItemContainer<Contact>(Contact.class);
	
	
	/*
	 * In this example layouts are programmed in Java. You may choose use a
	 * visual editor, CSS or HTML templates for layout instead.
	 */
	private void initLayout() {


		/* Build the component tree */
		VerticalLayout leftLayout = new VerticalLayout();
		addComponent(leftLayout);
		addComponent(editorLayout);
		leftLayout.addComponent(contactList);
		HorizontalLayout bottomLeftLayout = new HorizontalLayout();
		leftLayout.addComponent(bottomLeftLayout);
		bottomLeftLayout.addComponent(searchField);
		bottomLeftLayout.addComponent(addNewContactButton);

		/* Set the contents in the left of the split panel to use all the space */
		leftLayout.setSizeFull();

		/*
		 * On the left side, expand the size of the contactList so that it uses
		 * all the space left after from bottomLeftLayout
		 */
		leftLayout.setExpandRatio(contactList, 1);
		contactList.setSizeFull();

		/*
		 * In the bottomLeftLayout, searchField takes all the width there is
		 * after adding addNewContactButton. The height of the layout is defined
		 * by the tallest component.
		 */
		bottomLeftLayout.setWidth("100%");
		searchField.setWidth("100%");
		bottomLeftLayout.setExpandRatio(searchField, 1);

		/* Put a little margin around the fields in the right side editor */
		editorLayout.setMargin(true);
		editorLayout.setVisible(false);
	}
	
	private void initEditor() {

		editorLayout.addComponent(removeContactButton);
		

		/* User interface can be created dynamically to reflect underlying data. */
		for ( Field field : editorFields.getFields()) {
			editorLayout.addComponent(field);
			field.setWidth("100%");

		}

		/*
		 * Data can be buffered in the user interface. When doing so, commit()
		 * writes the changes to the data source. Here we choose to write the
		 * changes automatically without calling commit().
		 */
		editorFields.setBuffered(false);
	}
	
	private void initSearch() {

		/*
		 * We want to show a subtle prompt in the search field. We could also
		 * set a caption that would be shown above the field or description to
		 * be shown in a tooltip.
		 */
		searchField.setInputPrompt("Search contacts");

		/*
		 * Granularity for sending events over the wire can be controlled. By
		 * default simple changes like writing a text in TextField are sent to
		 * server with the next Ajax call. You can set your component to be
		 * immediate to send the changes to server immediately after focus
		 * leaves the field. Here we choose to send the text over the wire as
		 * soon as user stops writing for a moment.
		 */
		searchField.setTextChangeEventMode(TextChangeEventMode.LAZY);

		/*
		 * When the event happens, we handle it in the anonymous inner class.
		 * You may choose to use separate controllers (in MVC) or presenters (in
		 * MVP) instead. In the end, the preferred application architecture is
		 * up to you.
		 */
		searchField.addTextChangeListener(new TextChangeListener() {
			public void textChange(final TextChangeEvent event) {

				/* Reset the filter for the contactContainer. */
				contactContainer.removeAllContainerFilters();
				contactContainer.addContainerFilter(new ContactFilter(event
						.getText()));
			}
		});
	}
	
	/*
	 * A custom filter for searching names and companies in the
	 * contactContainer.
	 */
	private class ContactFilter implements Filter {
		private String needle;

		public ContactFilter(String needle) {
			this.needle = needle;
		}

		public boolean passesFilter(Object itemId, Item item) {
			Contact contact = (Contact) item;
			return contact.passFilter(needle);
		}

		public boolean appliesToProperty(Object id) {
			return true;
		}
	}





}
