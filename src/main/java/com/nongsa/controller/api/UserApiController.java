package com.nongsa.controller.api;

import com.nongsa.config.auth.PrincipalDetail;
import com.nongsa.handler.exception.CustomValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth/join")
    public ResponseEntity<?> save(@Valid @RequestBody User user, BindingResult bindingResult) {
//		User user = joinDto.toEntity();
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomValidationException("회원가입에 실패했습니다.", errorMap);
        } else {
            userService.회원가입(user);
            return new ResponseEntity<>(new ResponseDto<>(1, "회원가입성공", null), HttpStatus.OK);
        }
    }

    @PutMapping("/user")
    public ResponseEntity<?> update(
            @Valid @RequestBody User user,
            BindingResult bindingResult,
            @AuthenticationPrincipal PrincipalDetail principalDetail) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomValidationException("회원수정에 실패했습니다.", errorMap);
        } else {
            User userEntity = userService.회원수정(user);
            principalDetail.setUser(userEntity);
            return new ResponseEntity<>(new ResponseDto<>(1, "회원수정성공", null), HttpStatus.OK);
        }
    }

}
