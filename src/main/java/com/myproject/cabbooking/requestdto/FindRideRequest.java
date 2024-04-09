package com.myproject.cabbooking.requestdto;

import javax.validation.constraints.NotBlank;

import com.myproject.cabbooking.model.Location;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindRideRequest {
	@NotBlank(message = "Name is mandatory")
	String userName;

	@NotBlank(message = "Source is mandatory")
	Location source;

	@NotBlank(message = "Destination is mandatory")
	Location destination;
}
