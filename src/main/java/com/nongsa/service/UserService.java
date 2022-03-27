package com.nongsa.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nongsa.model.User;
import com.nongsa.repository.UserRepository;

@Service 
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public void 회원가입(User user) {
			userRepository.save(user);
	}
}
