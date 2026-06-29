package com.smartroutine.user.mapper;

import com.smartroutine.user.dto.UserResponse;
import com.smartroutine.user.security.CustomOAuth2User;

public class UserMapper {

    public static UserResponse toUserResponse(CustomOAuth2User user) {
        return new UserResponse(
            user.getUserId(), user.getEmail(), user.getUsername(), user.getRole()
        );
    }

}
