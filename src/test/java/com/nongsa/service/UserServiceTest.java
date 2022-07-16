package com.nongsa.service;

import com.nongsa.dto.JoinDto;
import com.nongsa.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public User createUser(){
        JoinDto joinDto = new JoinDto();
        joinDto.setEmail("test@email.com");
        joinDto.setUsername("홍길동");
        joinDto.setLocation("서울");
        joinDto.setCrop("딸기");
        joinDto.setPassword("1234");
        return User.createUser(joinDto, passwordEncoder);
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void saveUserTest(){
        User user = createUser();
        User savedUser = userService.saveUser(user);
        assertThat(savedUser.getUsername()).isEqualTo(user.getUsername());
        assertThat(savedUser.getPassword()).isEqualTo(user.getPassword());
        assertThat(savedUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(savedUser.getRole()).isEqualTo(user.getRole());
        assertThat(savedUser.getLocation()).isEqualTo(user.getLocation());
        assertThat(savedUser.getCrop()).isEqualTo(user.getCrop());
    }

    @Test
    @DisplayName("중복 회원 가입 테스트")
    public void saveDuplicateMemberTest(){
        User user1 = createUser();
        User user2 = createUser();
        userService.saveUser(user1);
        Throwable e = assertThrows(IllegalStateException.class, () -> {
            userService.saveUser(user2);});
        assertThat(e.getMessage()).isEqualTo("이미 가입된 회원입니다.");
    }
}