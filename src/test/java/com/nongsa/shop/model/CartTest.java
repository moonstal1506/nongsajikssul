package com.nongsa.shop.model;

import com.nongsa.shop.repository.CartRepository;
import com.nongsa.user.dto.JoinDto;
import com.nongsa.user.model.User;
import com.nongsa.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CartTest {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @PersistenceContext
    EntityManager em;

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
    @DisplayName("장바구니 회원 엔티티 매핑 조회 테스트")
    public void findCartAndMemberTest(){
        User user = createUser();
        userRepository.save(user);
        Cart cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);

        em.flush();
        em.clear();

        Cart savedCart = cartRepository.findById(cart.getId())
                .orElseThrow(EntityNotFoundException::new);
        Assertions.assertThat(savedCart.getUser().getId()).isEqualTo(user.getId());
    }
}