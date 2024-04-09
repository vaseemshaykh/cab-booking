package com.thinkify.cabbooking.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.thinkify.cabbooking.model.Driver;
import com.thinkify.cabbooking.model.ResponseModel;
import com.thinkify.cabbooking.model.User;
import com.thinkify.cabbooking.responsedto.ResponseUtility;
import com.thinkify.cabbooking.utility.Utility;

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
