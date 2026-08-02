package com.smartroutine.config;

import com.smartroutine.user.security.OAuth2LoginSuccessHandler;
import com.smartroutine.user.service.GoogleOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final GoogleOAuth2UserService googleOAuth2UserService;
    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
            .oauth2Login(oauth -> oauth
                .userInfoEndpoint(userInfo -> userInfo.userService(googleOAuth2UserService))
                .successHandler(oAuth2LoginSuccessHandler));


        return http.build();
    }

}
