package com.smartroutine.goal.dto;

import java.util.UUID;

public record GoalDeleteResponse(
    UUID goalId,
    String message
) {

}
