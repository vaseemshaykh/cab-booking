package com.myproject.cabbooking.requestdto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

	@NotBlank(message = "Name is mandatory")
	String name;

	@NotBlank(message = "Gender is mandatory")
	String gender;

	@NotBlank(message = "Age is mandatory")
	Integer age;

	// Assuming onRide is optional, no validation annotation needed
//    Boolean onRide;

}
