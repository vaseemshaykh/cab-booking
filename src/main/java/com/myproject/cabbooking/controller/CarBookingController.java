package com.myproject.cabbooking.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.cabbooking.exception.CustomException;
import com.myproject.cabbooking.requestdto.ChooseRideRequest;
import com.myproject.cabbooking.requestdto.DriverRequest;
import com.myproject.cabbooking.requestdto.FindRideRequest;
import com.myproject.cabbooking.requestdto.UserRequest;
import com.myproject.cabbooking.service.CarBookingServiceInterface;

@RestController
@RequestMapping("cabbooking/")
public class CarBookingController {

	@Autowired
	CarBookingServiceInterface carBookingServiceInterface;

	/**
	 * @author Shaik Vaseem
	 * @param userRequest
	 * @apiNote Method to add user details to application
	 * @throws CustomException
	 */
	@PostMapping("/addUser")
	public ResponseEntity<Object> addUser(@Valid @RequestBody UserRequest userRequest) throws CustomException {
		return carBookingServiceInterface.addUser(userRequest);
	}

	/**
	 * @author Shaik Vaseem
	 * @param driverRequest
	 * @apiNote Method to onBoard Driver into system
	 * @throws CustomException
	 */
	@PostMapping("/addDriver")
	public ResponseEntity<Object> addDriver(@Valid @RequestBody DriverRequest driverRequest) throws CustomException {
		return carBookingServiceInterface.addDriver(driverRequest);
	}

	/**
	 * @author Shaik Vaseem
	 * @param findRideRequest
	 * @apiNote Method to find rides available
	 * @throws CustomException
	 */
	@PostMapping("/findRide")
	public ResponseEntity<Object> findRide(@Valid @RequestBody FindRideRequest findRideRequest) throws CustomException {
		return carBookingServiceInterface.findRide(findRideRequest);
	}

	/**
	 * @author Shaik Vaseem
	 * @param chooseRideRequest
	 * @apiNote Method to select ride
	 * @throws CustomException
	 */
	@PostMapping("/chooseRide")
	public ResponseEntity<Object> chooseRide(@Valid @RequestBody ChooseRideRequest chooseRideRequest)
			throws CustomException {
		return carBookingServiceInterface.chooseRide(chooseRideRequest);
	}

}
