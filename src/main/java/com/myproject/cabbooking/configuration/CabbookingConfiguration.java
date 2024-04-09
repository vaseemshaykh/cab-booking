package com.myproject.cabbooking.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.myproject.cabbooking.model.Driver;
import com.myproject.cabbooking.model.ResponseModel;
import com.myproject.cabbooking.model.User;
import com.myproject.cabbooking.responsedto.ResponseUtility;
import com.myproject.cabbooking.utility.Utility;

@Configuration
public class CabbookingConfiguration {

	@Bean
	public User user() {
		return new User();
	}

	@Bean
	public Driver driver() {
		return new Driver();
	}

	@Bean
	public ResponseUtility responseUtility() {
		return new ResponseUtility();
	}

	@Bean
	public ResponseModel responseModel() {
		return new ResponseModel();
	}

	@Bean
	public Utility utility() {
		return new Utility();
	}

}
