package com.nongsa.dto;



import com.nongsa.model.User;

import lombok.Data;

@Data
public class JoinDto {
	
	private String username;

	private String password;
	
	private String email;
	
	private String crop;
	
	private String location;
	
	public User toEntity() {
		return User.builder()
				.username(username)
				.password(password)
				.email(email)
				.crop(crop)
				.location(location)
				.build();
	}
}
