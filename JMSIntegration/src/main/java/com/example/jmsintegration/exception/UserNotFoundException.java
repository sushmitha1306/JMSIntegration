package com.example.jmsintegration.exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = (Logger) LoggerFactory.getLogger(UserNotFoundException.class);
	public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
    	super(message);
    	logger.error("Error ----> {}", message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
