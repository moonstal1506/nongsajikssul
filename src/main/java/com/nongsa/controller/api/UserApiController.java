package com.nongsa.controller.api;

import com.nongsa.config.auth.PrincipalDetail;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nongsa.dto.JoinDto;
import com.nongsa.dto.ResponseDto;
import com.nongsa.model.User;
import com.nongsa.service.UserService;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
public class UserApiController {

	private final UserService userService;
	
	private final AuthenticationManager authenticationManager;

	@PostMapping("/auth/join")
	public ResponseDto<Integer> save(@Valid @RequestBody User user, BindingResult bindingResult) {
//		User user = joinDto.toEntity();
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user, @AuthenticationPrincipal PrincipalDetail principalDetail) {
		User userEntity = userService.회원수정(user);
		// 세션 등록
		principalDetail.setUser(userEntity);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

}
