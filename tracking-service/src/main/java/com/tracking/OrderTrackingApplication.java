package com.tracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.tracking", "com.tracking.exception"})
public class OrderTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderTrackingApplication.class, args);
	}

}
