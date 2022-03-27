package com.nongsa.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nongsa.dto.ResponseDto;
import com.nongsa.model.RoleType;
import com.nongsa.model.User;
import com.nongsa.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession session;
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {
		user.setRole(RoleType.USER); 
		userService.회원가입(user);

		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user){
		System.out.println("UserApiController:login 호출됨");
		User principal = userService.로그인(user); 

		if(principal !=null) {
			session.setAttribute("principal", principal);
		}


		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}
