package com.cybercom.vaadin.example;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

/* 
 * UI class is not managed by Spring 
 */
@Title("Simple bank")
@Push
public class SimpleBankUI extends UI
{

    @WebServlet(value = "/*", asyncSupported=true)
    @VaadinServletConfiguration(productionMode = false, ui = SimpleBankUI.class)
    public static class Servlet extends VaadinServlet {
    }
    
    @WebListener
    public static class Listener extends ContextLoaderListener {
    	// I wish no annotation for context parameter
		@Override
		public void contextInitialized(ServletContextEvent event) {
			event.getServletContext().setInitParameter("contextClass", "org.springframework.web.context.support.AnnotationConfigWebApplicationContext");
			event.getServletContext().setInitParameter("contextConfigLocation", "com.cybercom.vaadin.example");
			super.contextInitialized(event);
		}
    }

    @Override
    protected void init(VaadinRequest request) {
    	/*
    	 *  Below is "bridge" between Vaadin UI and Spring
    	 */
        TopView topView = WebApplicationContextUtils.getRequiredWebApplicationContext(
				VaadinServlet.getCurrent().getServletContext()).getBean(TopView.class);
        setContent(topView);
        
    }

}
