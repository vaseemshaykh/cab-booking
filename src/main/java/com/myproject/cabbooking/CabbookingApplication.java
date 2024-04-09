package com.myproject.cabbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.myproject.cabbooking"})
public class CabbookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CabbookingApplication.class, args);
	}

}
