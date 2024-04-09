package com.thinkify.cabbooking.utility;

import com.thinkify.cabbooking.model.Driver;
import com.thinkify.cabbooking.model.User;
import com.thinkify.cabbooking.requestdto.DriverRequest;
import com.thinkify.cabbooking.requestdto.UserRequest;

public class Utility {

	public  User convertUserRequestToUser(UserRequest userRequest) {
		User user = new User();

		user.setName(userRequest.getName());
		user.setGender(userRequest.getGender());
		user.setAge(userRequest.getAge());

		return user;
	}

	public  Driver convertDriverRequestToDriver(DriverRequest driverRequest) {

		Driver driver = new Driver();

		driver.setName(driverRequest.getName());
		driver.setGender(driverRequest.getGender());
		driver.setAge(driverRequest.getAge());
		driver.setCarName(driverRequest.getCarName());
		driver.setCarNumber(driverRequest.getCarNumber());
		driver.setLocation(driverRequest.getLocation());

		return driver;
	}

}
