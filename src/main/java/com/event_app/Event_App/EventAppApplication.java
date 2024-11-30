package com.event_app.Event_App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class EventAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventAppApplication.class, args);
	}

}
