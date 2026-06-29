package com.smartroutine.user.dto;

import com.smartroutine.user.entity.Role;
import java.util.UUID;

public record UserResponse(
     UUID userId,
     String email,
     String name,
     Role role
) {

}
