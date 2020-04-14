package com.example.jmsintegration;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class JmsIntegrationApplication {

	private static final Logger logger = LoggerFactory.getLogger(JmsIntegrationApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(JmsIntegrationApplication.class);
		logger.debug("--Application Started--");
	}
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}

}
