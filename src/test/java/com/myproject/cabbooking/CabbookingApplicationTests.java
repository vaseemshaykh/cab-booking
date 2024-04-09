package com.myproject.cabbooking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import com.myproject.cabbooking.exception.CustomException;
import com.myproject.cabbooking.model.Driver;
import com.myproject.cabbooking.model.Location;
import com.myproject.cabbooking.model.User;
import com.myproject.cabbooking.requestdto.ChooseRideRequest;
import com.myproject.cabbooking.requestdto.DriverRequest;
import com.myproject.cabbooking.requestdto.FindRideRequest;
import com.myproject.cabbooking.requestdto.UserRequest;
import com.myproject.cabbooking.responsedto.ResponseUtility;
import com.myproject.cabbooking.serviceimp.CarBookingService;
import com.myproject.cabbooking.utility.Utility;

@SpringBootTest
class CarBookingServiceTests {

	private CarBookingService carBookingService;
	private ResponseUtility responseUtility;
	private Utility utility;

	@BeforeEach
	void setUp() {
		responseUtility = mock(ResponseUtility.class);
		utility = mock(Utility.class);
		carBookingService = new CarBookingService(responseUtility, utility);
	}

	@Test
	void testAddUserSuccess() throws Exception {
		// Mock data
		UserRequest userRequest = new UserRequest();
		userRequest.setName("Rahul");
		userRequest.setGender("M");
		userRequest.setAge(23);

		User user = new User();
		user.setId(UUID.randomUUID());

		// Mock behavior
		when(utility.convertUserRequestToUser(userRequest)).thenReturn(user);

		// Mock response entity object
		ResponseEntity<Object> mockResponseEntity = ResponseEntity.ok().build();

		// Mock behavior
		when(responseUtility.createResponse(anyInt(), anyString(), anyString(), eq(userRequest)))
				.thenReturn(mockResponseEntity);

		// Test the method
		carBookingService.addUser(userRequest);

		// Verify the interactions
		verify(utility, times(1)).convertUserRequestToUser(userRequest);
		verify(responseUtility, times(1)).createResponse(anyInt(), anyString(), anyString(), eq(userRequest));

	}

	@Test
	void testAddDriverSuccess() throws Exception {
		// Mock data
		DriverRequest driverRequest = new DriverRequest();
		driverRequest.setName("Driver1");
		driverRequest.setGender("M");
		driverRequest.setAge(22);
		driverRequest.setCarName("Swift");
		driverRequest.setCarNumber("KA-01-12345");

		Location location = new Location();
		location.setX(10);
		location.setY(1);

		driverRequest.setLocation(location);

		Driver driver = new Driver();
		driver.setId(UUID.randomUUID());

		// Mock behavior
		when(utility.convertDriverRequestToDriver(driverRequest)).thenReturn(driver);

		// Mock response entity object
		ResponseEntity<Object> mockResponseEntity = ResponseEntity.ok().build();

		// Mock behavior
		when(responseUtility.createResponse(anyInt(), anyString(), anyString(), eq(driverRequest)))
				.thenReturn(mockResponseEntity);

		// Test the method
		carBookingService.addDriver(driverRequest);

		// Verify the interactions
		verify(utility, times(1)).convertDriverRequestToDriver(driverRequest);
		verify(responseUtility, times(1)).createResponse(anyInt(), anyString(), anyString(), eq(driverRequest));
	}

	@Test
	void testFindRideSuccess() throws CustomException {
		// Mock data
		FindRideRequest findRideRequest = new FindRideRequest();
		findRideRequest.setUserName("Rahul");
		Location source = new Location(10, 0);
		Location destination = new Location(15, 3);
		findRideRequest.setSource(source);
		findRideRequest.setDestination(destination);

		// Set up the list of drivers and users
		List<Driver> listOfDrivers = createMockDrivers();
		List<User> listOfUser = createMockUsers();

		// Set up the CarBookingService instance to use the mocked responseUtility
		carBookingService = new CarBookingService(responseUtility, utility);
		ReflectionTestUtils.setField(carBookingService, "listOfUser", listOfUser);
		ReflectionTestUtils.setField(carBookingService, "listOfDrivers", listOfDrivers);
		ReflectionTestUtils.setField(carBookingService, "distanceLimit", 5);

		// Mock behavior
		when(responseUtility.createResponse(eq(HttpStatus.OK.value()), eq("SUCCESS"), eq("Ride found Successfully!"),
				anyList())).thenReturn(ResponseEntity.ok(listOfDrivers));

		// Test the method
		ResponseEntity<Object> response = carBookingService.findRide(findRideRequest);

		// Verify the interactions
		verify(responseUtility, times(1)).createResponse(eq(HttpStatus.OK.value()), eq("SUCCESS"),
				eq("Ride found Successfully!"), anyList());

		// Assertions
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertTrue(response.getBody() instanceof List);
	}

	@Test
	void testChooseRideSuccess() throws CustomException {
		// Set up the list of drivers and users
		List<Driver> listOfDrivers = createMockDrivers();
		List<User> listOfUsers = createMockUsers();

		// Set up the CarBookingService instance to use the mocked responseUtility
		carBookingService = new CarBookingService(responseUtility, utility);
		ReflectionTestUtils.setField(carBookingService, "listOfUser", listOfUsers);
		ReflectionTestUtils.setField(carBookingService, "listOfDrivers", listOfDrivers);
		ReflectionTestUtils.setField(carBookingService, "distanceLimit", 5);

		ChooseRideRequest chooseRideRequest = new ChooseRideRequest();
		chooseRideRequest.setUserName("Rahul");
		chooseRideRequest.setDriverName("Driver1");

		Driver selectedDriver = listOfDrivers.get(0); // Get the first driver as the selected driver
//	    selectedDriver.setOnRide(true);

		// Mock behavior to return a response with the selected driver
		when(responseUtility.createResponse(eq(HttpStatus.OK.value()), eq("SUCCESS"), eq("Ride Selected Successfully!"),
				eq(selectedDriver))).thenReturn(ResponseEntity.ok().build());

		// Test the method
		ResponseEntity<Object> response = carBookingService.chooseRide(chooseRideRequest);

		// Verify the interactions
		verify(responseUtility, times(1)).createResponse(eq(HttpStatus.OK.value()), eq("SUCCESS"),
				eq("Ride Selected Successfully!"), eq(selectedDriver));

		// Assertions
		assertEquals(HttpStatus.OK, response.getStatusCode());
//	    assertNotNull(response.getBody());
	}

	// Helper method to create mock drivers
	private List<Driver> createMockDrivers() {
		List<Driver> listOfDrivers = new LinkedList<>();
		listOfDrivers.add(
				new Driver(UUID.randomUUID(), "Driver1", "M", 22, "Swift", "KA-01-12345", new Location(10, 1), false));
		listOfDrivers.add(new Driver(UUID.randomUUID(), "Driver2", "M", 29, "Fortuner", "KA-01-12346",
				new Location(11, 10), false));
		listOfDrivers.add(
				new Driver(UUID.randomUUID(), "Driver3", "M", 29, "Mustang", "KA-01-12347", new Location(5, 3), false));
		return listOfDrivers;
	}

	// Helper method to create mock users
	private List<User> createMockUsers() {
		List<User> listOfUsers = new LinkedList<>();
		listOfUsers.add(new User(UUID.randomUUID(), "Abhishek", "M", 23));
		listOfUsers.add(new User(UUID.randomUUID(), "Rahul", "M", 29));
		listOfUsers.add(new User(UUID.randomUUID(), "Nadhini", "F", 22));
		return listOfUsers;
	}
}