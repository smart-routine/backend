package com.smartroutine.user.controller;

import com.smartroutine.user.dto.UserResponse;
import com.smartroutine.user.security.CustomOAuth2User;
import com.smartroutine.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMe(@AuthenticationPrincipal CustomOAuth2User user) {
        UserResponse response = userService.getMe(user);

        return ResponseEntity.ok(response);
    }

}
