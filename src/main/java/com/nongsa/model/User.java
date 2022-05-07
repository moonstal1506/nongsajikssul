package com.nongsa.model;


import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Size(min = 2, max = 20)
	@NotBlank(message = "아이디는 필수 입력값입니다.")
	@Column(unique=true)
	private String username;

	@NotBlank(message = "비밀번호는 필수 입력값입니다.")
//	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$\n", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
	private String password;

	@NotBlank(message = "이메일은 필수 입력값입니다.")
//	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
	@Column(unique=true)
	private String email;
	
	@Column(length =50)
	private String crop;
	
	@Column(length =50)
	private String location;
	
	@Enumerated(EnumType.STRING)
	private RoleType role;
	
	private String oauth;//kakao,google

	@OneToMany(mappedBy="user", fetch =FetchType.LAZY)
	@JsonIgnoreProperties({"user"})
	private List<Board> boards;
	
	@CreationTimestamp
	private Timestamp createDate;
}
