package com.nongsa.user.model;

import com.nongsa.user.dto.JoinDto;
import com.nongsa.user.repository.UserRepository;
import com.nongsa.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserTest {

    @Autowired
    private UserService userService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @PersistenceContext
    EntityManager em;

    public User createUser(){
        JoinDto joinDto = new JoinDto();
        joinDto.setEmail("test@email.com");
        joinDto.setUsername("홍길동");
        joinDto.setLocation("서울");
        joinDto.setCrop("딸기");
        joinDto.setPassword("1234");
        User user = User.createUser(joinDto, passwordEncoder);
        return userService.saveUser(user);
    }

    @Test
    @DisplayName("Auditing 테스트")
    @WithMockUser(username = "홍길동", roles = "ADMIN")
    public void auditingTest(){
        LocalDateTime now = LocalDateTime.of(2022, 9, 3, 0, 0, 0);
        User newUser = createUser();

        em.flush();
        em.clear();

        User user = userRepository.findById(newUser.getId())
                .orElseThrow(EntityNotFoundException::new);

        assertThat(user.getRegTime()).isAfter(now);
        assertThat(user.getUpdateTime()).isAfter(now);
        assertThat(user.getCreatedBy()).isEqualTo("홍길동");
        assertThat(user.getModifiedBy()).isEqualTo("홍길동");
    }

}