package com.myproject.cabbooking.utility;

import com.myproject.cabbooking.model.Driver;
import com.myproject.cabbooking.model.User;
import com.myproject.cabbooking.requestdto.DriverRequest;
import com.myproject.cabbooking.requestdto.UserRequest;

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
