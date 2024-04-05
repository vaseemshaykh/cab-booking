package com.thinkify.cabbooking.service;

import org.springframework.http.ResponseEntity;

import com.thinkify.cabbooking.exception.CustomException;
import com.thinkify.cabbooking.requestdto.ChooseRideRequest;
import com.thinkify.cabbooking.requestdto.DriverRequest;
import com.thinkify.cabbooking.requestdto.FindRideRequest;
import com.thinkify.cabbooking.requestdto.UserRequest;

public interface CarBookingServiceInterface {

	public ResponseEntity<Object> addUser(UserRequest userRequest) throws CustomException;

	public ResponseEntity<Object> addDriver(DriverRequest driverRequest) throws CustomException;

	public ResponseEntity<Object> findRide( FindRideRequest findRideRequest) throws CustomException;

	public ResponseEntity<Object> chooseRide(ChooseRideRequest chooseRideRequest) throws CustomException;

}
