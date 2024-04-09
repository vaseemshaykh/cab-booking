package com.thinkify.cabbooking.model;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class User {

	public User(UUID randomUUID, String name, String gender, int age) {
		this.id = randomUUID;
		this.name = name;
		this.gender = gender;
		this.age = age;
	}

	UUID id;

	String name;

	String gender;

	Integer age;

	Boolean onRide;

}
