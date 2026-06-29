package com.smartroutine.user.service;

import com.smartroutine.user.entity.Provider;
import com.smartroutine.user.entity.Role;
import com.smartroutine.user.entity.User;
import com.smartroutine.user.security.CustomOAuth2User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoogleOAuth2UserService extends DefaultOAuth2UserService {

    private final UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest){

        OAuth2User oAuth2User = super.loadUser(userRequest);

        User user = User.builder()
            .name(oAuth2User.getAttribute("name"))
            .email(oAuth2User.getAttribute("email"))
            .provider(Provider.GOOGLE)
            .providerId(oAuth2User.getAttribute("sub"))
            .profileImageUrl(oAuth2User.getAttribute("picture"))
            .role(Role.USER)
            .build();

        User savedUser = userService.findOrSaveGoogleUser(user);

        return new CustomOAuth2User(
            savedUser, oAuth2User.getAttributes(), oAuth2User.getAuthorities()
        );
    }

}
