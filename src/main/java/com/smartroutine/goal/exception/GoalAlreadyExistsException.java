package com.smartroutine.goal.exception;

public class GoalAlreadyExistsException extends RuntimeException {

    public GoalAlreadyExistsException(String goalName) {
        super("Goal with name " + goalName + " already exists");
    }
}
