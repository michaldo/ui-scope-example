package com.cybercom.vaadin.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cybercom.vaadin.spring.UIScope;

@Configuration
public class Conf {
	
	@Bean static UIScope uiScope() {
		return new UIScope();
	}
	
}
