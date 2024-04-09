package com.myproject.cabbooking.service;

import org.springframework.http.ResponseEntity;

import com.myproject.cabbooking.exception.CustomException;
import com.myproject.cabbooking.requestdto.ChooseRideRequest;
import com.myproject.cabbooking.requestdto.DriverRequest;
import com.myproject.cabbooking.requestdto.FindRideRequest;
import com.myproject.cabbooking.requestdto.UserRequest;

public interface CarBookingServiceInterface {

	public ResponseEntity<Object> addUser(UserRequest userRequest) throws CustomException;

	public ResponseEntity<Object> addDriver(DriverRequest driverRequest) throws CustomException;

	public ResponseEntity<Object> findRide( FindRideRequest findRideRequest) throws CustomException;

	public ResponseEntity<Object> chooseRide(ChooseRideRequest chooseRideRequest) throws CustomException;

}
