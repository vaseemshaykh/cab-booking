package com.myproject.cabbooking.responsedto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.myproject.cabbooking.model.ResponseModel;

public class ResponseUtility {

	@Autowired
	ResponseModel responseModel;

	public ResponseEntity<Object> createResponse(Integer statusCode, String status, String message, Object data) {

		responseModel.setStatusCode(statusCode);
		responseModel.setStatus(status);
		responseModel.setMessage(message);
		responseModel.setData(data);

		if (status.equalsIgnoreCase("SUCCESS")) {
			return ResponseEntity.status(HttpStatus.OK).body(responseModel);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseModel);
		}

	}
}
