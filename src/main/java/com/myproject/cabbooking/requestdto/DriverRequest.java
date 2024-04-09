package com.myproject.cabbooking.requestdto;

import javax.validation.constraints.NotBlank;

import com.myproject.cabbooking.model.Location;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverRequest {

	@NotBlank(message = "Name is manditory")
	String name;

	@NotBlank(message = "Gender is manditory")
	String gender;

	@NotBlank(message = "Age is manditory")
	Integer age;

	@NotBlank(message = "Car Name is manditory")
	String carName;

	@NotBlank(message = "Car Number is manditory")
	String carNumber;

	@NotBlank(message = "Location is manditory")
	Location location;

}
