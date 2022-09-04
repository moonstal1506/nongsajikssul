package com.nongsa.user.service;

import com.nongsa.user.dto.UserPageDto;
import com.nongsa.handler.exception.CustomException;
import com.nongsa.sns.repository.SubscribeRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nongsa.user.model.User;
import com.nongsa.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional(readOnly = true)
    public UserPageDto userPage(Long pageUserId, Long principalId) {
        UserPageDto dto = new UserPageDto();
        User userEntity = userRepository.findById(pageUserId).orElseThrow(() -> {
            throw new CustomException("해당 페이지는 없는 페이지입니다.");
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
    public User saveUser(User user){
        validateDuplicateMember(user);
        return userRepository.save(user);
    }

    private void validateDuplicateMember(User user){
        User findUser = userRepository.findByUsername(user.getUsername());
        if(findUser != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Transactional
    public User update(User user) {

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
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
