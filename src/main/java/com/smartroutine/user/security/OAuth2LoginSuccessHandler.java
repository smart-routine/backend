package com.smartroutine.user.security;

import com.smartroutine.user.entity.User;
import com.smartroutine.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication
    ) throws IOException{

        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

        User user = userRepository.findById(oAuth2User.getUserId()).orElseThrow();

        OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) authentication;

        OAuth2AuthorizedClient authorizedClient = oAuth2AuthorizedClientService.loadAuthorizedClient(
            authenticationToken.getAuthorizedClientRegistrationId(),
            authenticationToken.getName()
        );

        String accessToken = authorizedClient.getAccessToken().getTokenValue();
        String refreshToken = authorizedClient.getRefreshToken() != null ? authorizedClient.getRefreshToken().getTokenValue() : null;

        LocalDateTime expiresAt = authorizedClient.getAccessToken()
                .getExpiresAt()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        user.updateGoogleToken(accessToken, refreshToken, expiresAt);

        response.sendRedirect("/api/users/me");
    }


}
