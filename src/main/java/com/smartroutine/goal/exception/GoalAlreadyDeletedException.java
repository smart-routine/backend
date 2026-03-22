package com.smartroutine.goal.exception;

import java.util.UUID;

public class GoalAlreadyDeletedException extends RuntimeException {

    public GoalAlreadyDeletedException(UUID goalId) {
        super(goalId.toString() +" already deleted");
    }
}
