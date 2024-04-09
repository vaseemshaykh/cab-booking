package com.myproject.cabbooking.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseModel {
	
	Integer statusCode;
	
	String status;
	
	String message;
	
	Object data;

}
