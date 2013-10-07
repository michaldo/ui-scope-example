package com.cybercom.vaadin.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.cybercom.vaadin.spring.UIScope;

@Configuration
@EnableScheduling
public class Conf {
	
	@Bean static UIScope uiScope() {
		return new UIScope();
	}
	
}
