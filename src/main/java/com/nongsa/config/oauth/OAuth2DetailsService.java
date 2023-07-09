package com.nongsa.config.oauth;

import com.nongsa.config.auth.PrincipalDetails;
import com.nongsa.user.constant.RoleType;
import com.nongsa.user.model.User;
import com.nongsa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OAuth2DetailsService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getRegistrationId();
        OAuth2UserInfo oAuth2UserInfo = getOAuth2UserInfo(oauth2User, provider);

        String providerId = oAuth2UserInfo.getProviderId();
        String username = provider + "_" + providerId;
        User userEntity = userRepository.findByUsername(username);

        if (userEntity == null) {
            userEntity = saveUser(provider, oAuth2UserInfo, username);
        }
        return new PrincipalDetails(userEntity, oauth2User.getAttributes());
    }

    private OAuth2UserInfo getOAuth2UserInfo(OAuth2User oauth2User, String provider) {
        Map<String, Object> userInfo = oauth2User.getAttributes();

        if (provider.equals("kakao")) {
            return new KakaoUserInfo(userInfo);
        }

        return new FacebookUserInfo(userInfo);
    }

    private User saveUser(String provider, OAuth2UserInfo oAuth2UserInfo, String username) {
        String uuid = UUID.randomUUID().toString().substring(0, 6);
        User user = User.builder()
                .username(username)
                .password(new BCryptPasswordEncoder().encode(uuid))
                .email(oAuth2UserInfo.getEmail())
                .role(RoleType.USER)
                .oauth(provider)
                .build();
        return userRepository.save(user);
    }

}
