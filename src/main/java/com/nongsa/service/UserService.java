package com.nongsa.service;

import com.nongsa.dto.UserPageDto;
import com.nongsa.repository.SubscribeRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nongsa.model.RoleType;
import com.nongsa.model.User;
import com.nongsa.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional(readOnly = true)
    public UserPageDto 회원페이지(int pageUserId, int principalId) {
        UserPageDto dto = new UserPageDto();
        User userEntity = userRepository.findById(pageUserId).orElseThrow(() -> {
            throw new IllegalStateException("해당 페이지는 없는 페이지입니다.");
        });
        dto.setUser(userEntity);
        dto.setPageOwnerState(pageUserId == principalId);
        dto.setBoardCount(userEntity.getBoards().size());

        int subscribeState = subscribeRepository.subscribeState(principalId, pageUserId);
        int subscribeCount = subscribeRepository.subscribeCount(pageUserId);
        int subscribedCount = subscribeRepository.subscribedCount(pageUserId);

        dto.setSubscribeState(subscribeState == 1);
        dto.setSubscribeCount(subscribeCount);
        dto.setSubscribedCount(subscribedCount);

        return dto;
    }

    @Transactional
    public void 회원가입(User user) {
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

    @Transactional
    public User 회원수정(User user) {

        User persistence = userRepository.findById(user.getId()).orElseThrow(() -> new IllegalArgumentException("회원 찾기 실패"));

        if (persistence.getOauth() == null || persistence.getOauth().equals("")) {
            String rawPassword = user.getPassword();
            String encPassword = encoder.encode(rawPassword);
            persistence.setPassword(encPassword);
        }
        persistence.setEmail(user.getEmail());
        persistence.setCrop(user.getCrop());
        persistence.setLocation(user.getLocation());

        return persistence;
    }

    @Transactional(readOnly = true)
    public User 회원찾기(String username) {
        return userRepository.findByUsername(username);
    }

}
