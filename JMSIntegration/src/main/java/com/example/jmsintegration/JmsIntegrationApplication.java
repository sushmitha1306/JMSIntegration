package com.example.jmsintegration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class JmsIntegrationApplication {

	private static final Logger logger = LoggerFactory.getLogger(JmsIntegrationApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(JmsIntegrationApplication.class);
		logger.debug("--Application Started--");
	}

}
