package com.nongsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nongsa.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
