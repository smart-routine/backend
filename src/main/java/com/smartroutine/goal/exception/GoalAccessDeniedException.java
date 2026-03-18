package com.smartroutine.goal.exception;

import java.util.UUID;

public class GoalAccessDeniedException extends RuntimeException {

    public GoalAccessDeniedException(UUID goalId, UUID userId) {
        super(goalId + " access denied for user " + userId);
    }
}
