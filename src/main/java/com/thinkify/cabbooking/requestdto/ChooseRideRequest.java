package com.thinkify.cabbooking.requestdto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChooseRideRequest {

	@NotBlank(message = "userName is mandatory")
	String userName;

	@NotBlank(message = "driverName is mandatory")
	String driverName;

}
