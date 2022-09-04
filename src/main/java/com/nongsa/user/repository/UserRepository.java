package com.nongsa.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nongsa.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
