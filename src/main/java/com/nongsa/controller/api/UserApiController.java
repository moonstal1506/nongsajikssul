package com.nongsa.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nongsa.dto.ResponseDto;
import com.nongsa.model.User;

@RestController
public class UserApiController {
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}
