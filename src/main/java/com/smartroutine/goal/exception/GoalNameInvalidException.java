package com.smartroutine.goal.exception;

public class GoalNameInvalidException extends IllegalArgumentException {

    public GoalNameInvalidException(String goalName) {
        super(goalName + " is not a valid GoalName");
    }
}
