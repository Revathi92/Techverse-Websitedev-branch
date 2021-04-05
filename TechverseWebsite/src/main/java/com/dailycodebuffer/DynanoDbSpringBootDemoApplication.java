package com.dailycodebuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DynanoDbSpringBootDemoApplication {
	
	private static final Logger LOGGER= LoggerFactory.getLogger(DynanoDbSpringBootDemoApplication.class);
	

	public static void main(String[] args) {
		LOGGER.info("DynanoDbSpringBootDemoApplication - Main() :: Begning");		
		SpringApplication.run(DynanoDbSpringBootDemoApplication.class, args);
		LOGGER.info("DynanoDbSpringBootDemoApplication - Main() :: Starting");	
	}

}
