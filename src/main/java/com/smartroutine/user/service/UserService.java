package com.smartroutine.user.service;

import com.smartroutine.user.dto.UserResponse;
import com.smartroutine.user.entity.Provider;
import com.smartroutine.user.entity.User;
import com.smartroutine.user.mapper.UserMapper;
import com.smartroutine.user.repository.UserRepository;
import com.smartroutine.user.security.CustomOAuth2User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User findOrSaveGoogleUser(User user) {
        return userRepository.findByProviderAndProviderId(Provider.GOOGLE, user.getProviderId())
                .orElseGet(() -> userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public UserResponse getMe(CustomOAuth2User user) {
        return UserMapper.toUserResponse(user);
    }
}
