package com.smartroutine.goal.exception;

import java.util.UUID;

public class GoalNotFoundException extends RuntimeException {

    public GoalNotFoundException(UUID goalId) {
        super("Not found goal: " + goalId);
    }
}
