package com.nongsa.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

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
	
	@Column(nullable=false, length =100)
	private String username;
	
	@Column(nullable=false, length =100)
	private String password;
	
	@Column(nullable=false, length =50)
	private String email;
	
	@Column(nullable=true,length =50)
	private String crop;
	
	@Column(nullable=true,length =50)
	private String location;
	
	@ColumnDefault("'user'")
	private String role;
	
	@CreationTimestamp
	private Timestamp createDate;
}
