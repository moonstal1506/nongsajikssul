package com.nongsa.config.oauth;

import com.nongsa.config.auth.PrincipalDetails;
import com.nongsa.model.RoleType;
import com.nongsa.model.User;
import com.nongsa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OAuth2DetailsService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);

        Map<String, Object> userInfo = oauth2User.getAttributes();
        String username = "행복한 농부_" + userInfo.get("id");
        String password = new BCryptPasswordEncoder().encode(UUID.randomUUID().toString());
        String email = (String) userInfo.get("email");

       User userEntity = userRepository.findByUsername(username);

        if (userEntity == null) {
            User user = User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .role(RoleType.USER)
                    .oauth("facebook")
                    .build();
            return new PrincipalDetails(userRepository.save(user), oauth2User.getAttributes());
        } else {
            return new PrincipalDetails(userEntity, oauth2User.getAttributes());
        }
    }
}
