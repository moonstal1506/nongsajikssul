package com.nongsa.controller;

import com.nongsa.dto.JoinDto;
import com.nongsa.model.User;
import com.nongsa.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    public void createUser(){
        JoinDto joinDto = new JoinDto();
        joinDto.setEmail("test@email.com");
        joinDto.setUsername("홍길동");
        joinDto.setLocation("서울");
        joinDto.setCrop("딸기");
        joinDto.setPassword("1234");
        User user = User.createUser(joinDto, passwordEncoder);
        userService.saveUser(user);
    }

    @Test
    @DisplayName("상품 등록 페이지 권한 테스트")
    @WithUserDetails(value = "홍길동", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void itemFormTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/item/new"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("상품 등록 페이지 일반 회원 접근 테스트")
    @WithMockUser(username = "user", roles = "USER")
    public void itemFormNotAdminTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/item/new"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
}