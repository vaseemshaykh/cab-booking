package com.myproject.cabbooking.serviceimp;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.myproject.cabbooking.exception.CustomException;
import com.myproject.cabbooking.model.Driver;
import com.myproject.cabbooking.model.User;
import com.myproject.cabbooking.requestdto.ChooseRideRequest;
import com.myproject.cabbooking.requestdto.DriverRequest;
import com.myproject.cabbooking.requestdto.FindRideRequest;
import com.myproject.cabbooking.requestdto.UserRequest;
import com.myproject.cabbooking.responsedto.ResponseUtility;
import com.myproject.cabbooking.service.CarBookingServiceInterface;
import com.myproject.cabbooking.utility.Utility;

@Service
public class CarBookingService implements CarBookingServiceInterface {

	 List<User> listOfUser = new LinkedList<>();
	 List<Driver> listOfDrivers = new LinkedList<>();

	private User user;
	private Driver driver;
	private ResponseUtility responseUtility;

	private Integer distanceLimit;

	private Utility utility;

	public CarBookingService(ResponseUtility responseUtility, Utility utility) {
		this.responseUtility = responseUtility;
		this.utility = utility;
	}

	@Autowired
	public void setUtility(Utility utility) {
		this.utility = utility;
	}

	@Autowired
	public void setUser(User user) {
		this.user = user;
	}

	@Autowired
	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	@Autowired
	public void setResponseUtility(ResponseUtility responseUtility) {
		this.responseUtility = responseUtility;
	}

	@Value("${distance.limit.to.find.ride}")
	public void setDistanceLimit(Integer distanceLimit) {
		this.distanceLimit = distanceLimit;
	}

	private static final Logger logger = LogManager.getLogger(CarBookingService.class);

	/**
	 * @author Shaik Vaseem
	 * @param userRequest
	 * @apiNote Method to add user details to application
	 * @throws CustomException
	 */
	@Override
	public ResponseEntity<Object> addUser(UserRequest userRequest) throws CustomException {
		logger.info("addUser service method started");
		if (StringUtils.isEmpty(userRequest.getName()) || StringUtils.isEmpty(userRequest.getGender())
				|| userRequest.getAge() == null || userRequest.getAge() == 0) {
			throw new CustomException("Mandatory Fields missing");
		}
		try {

			user = utility.convertUserRequestToUser(userRequest);
			user.setId(UUID.randomUUID());

			listOfUser.add(user);

			return responseUtility.createResponse(HttpStatus.OK.value(), "SUCCESS", "User Details added Succesfully!",
					userRequest);
		} catch (Exception e) {
			return responseUtility.createResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "ERROR",
					"User Details failed to add please try again later!" + e.getMessage(), null);
		}
	}

	/**
	 * @author Shaik Vaseem
	 * @param driverRequest
	 * @apiNote Method to onBoard Driver into system
	 * @throws CustomException
	 */
	@Override
	public ResponseEntity<Object> addDriver(@Valid DriverRequest driverRequest) throws CustomException {
		logger.info("addDriver service method started");
		if (driverRequest.getName() == null || driverRequest.getGender() == null || driverRequest.getAge() == null
				|| driverRequest.getCarName() == null || driverRequest.getCarNumber() == null
				|| driverRequest.getLocation() == null) {
			throw new CustomException("Mandatory Fields missing");
		}

		try {
			driver = utility.convertDriverRequestToDriver(driverRequest);
			driver.setId(UUID.randomUUID());
			driver.setOnRide(false);

			listOfDrivers.add(driver);

			return responseUtility.createResponse(HttpStatus.OK.value(), "SUCCESS", "Driver On-boarded Successfully!",
					driverRequest);

		} catch (Exception e) {
			return responseUtility.createResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "ERROR",
					"Driver On-boarded failed please try again later!" + e.getMessage(), null);

		}
	}

	/**
	 * @author Shaik Vaseem
	 * @param findRideRequest
	 * @apiNote Method to find ride
	 * @throws CustomException
	 */
	@Override
	public ResponseEntity<Object> findRide(@Valid FindRideRequest findRideRequest) throws CustomException {
		logger.info("findRide service method started");
		if (findRideRequest.getUserName() == null || findRideRequest.getSource() == null
				|| findRideRequest.getDestination() == null) {
			throw new CustomException("Mandatory Fields missing");
		}

		try {

			List<Driver> listOfAvailableDriver = new LinkedList<>();
			int x1 = findRideRequest.getSource().getX();
			int y1 = findRideRequest.getSource().getY();

			for (Driver dri : listOfDrivers) {
				if (Math.sqrt(Math.pow(dri.getLocation().getX() - x1, 2)
						+ Math.pow(dri.getLocation().getY() - y1, 2)) < distanceLimit) {
					if (!dri.getOnRide()) {
						listOfAvailableDriver.add(dri);
					}
				}
			}

			if (listOfAvailableDriver.isEmpty()) {
				return responseUtility.createResponse(HttpStatus.OK.value(), "SUCCESS",
						"Could not find Ride. Please try again later!", null);
			} else {
				return responseUtility.createResponse(HttpStatus.OK.value(), "SUCCESS", "Ride found Successfully!",
						listOfAvailableDriver);
			}

		} catch (Exception e) {
			return responseUtility.createResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "ERROR",
					"Could not find Ride. Please try again later!" + e.getMessage(), null);

		}
	}

	/**
	 * @author Shaik Vaseem
	 * @param chooseRideRequest
	 * @apiNote Method to select ride
	 * @throws CustomException
	 */
	@Override
	public ResponseEntity<Object> chooseRide(@Valid ChooseRideRequest chooseRideRequest) throws CustomException {
		logger.info("chooseRide service method started");
		if (chooseRideRequest.getUserName() == null || chooseRideRequest.getDriverName() == null) {
			throw new CustomException("Mandatory Fields missing");
		}

		try {

			for (Driver dri : listOfDrivers) {
				if (chooseRideRequest.getDriverName().equals(dri.getName())) {
					dri.setOnRide(true);
					return responseUtility.createResponse(HttpStatus.OK.value(), "SUCCESS",
							"Ride Selected Successfully!", dri);
				}
			}

			return responseUtility.createResponse(HttpStatus.OK.value(), "SUCCESS", "Ride Failed to Select!", null);

		} catch (Exception e) {
			return responseUtility.createResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "ERROR",
					"Could not choose Ride. Please try again later!" + e.getMessage(), null);
		}
	}

}
