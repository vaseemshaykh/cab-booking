package com.myproject.cabbooking.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Driver {

	UUID id;

	String name;

	String gender;

	Integer age;

	String carName;

	String carNumber;

	Location location;

	Boolean onRide;

}
