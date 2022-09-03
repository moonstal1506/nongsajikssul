package com.nongsa.controller;

import com.nongsa.dto.JoinDto;
import com.nongsa.model.User;
import com.nongsa.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserControllerTest {

    @Autowired
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public User createUser(String username, String password){
        JoinDto joinDto = new JoinDto();
        joinDto.setEmail("test@email.com");
        joinDto.setUsername(username);
        joinDto.setLocation("서울");
        joinDto.setCrop("딸기");
        joinDto.setPassword(password);
        User user = User.createUser(joinDto, passwordEncoder);
        return userService.saveUser(user);
    }

    @Test
    @DisplayName("로그인 성공 테스트")
    public void loginSuccessTest() throws Exception{
        String username = "홍길동";
        String password = "1234";
        this.createUser(username, password);
        mockMvc.perform(formLogin().userParameter("username")
                        .loginProcessingUrl("/auth/login")
                        .user(username).password(password))
                .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }

    @Test
    @DisplayName("로그인 실패 테스트")
    public void loginFailTest() throws Exception{
        String username = "홍길동";
        String password = "1234";
        this.createUser(username, password);
        mockMvc.perform(formLogin().userParameter("username")
                        .loginProcessingUrl("/auth/login")
                        .user(username).password("12345"))
                .andExpect(SecurityMockMvcResultMatchers.unauthenticated());
    }
}