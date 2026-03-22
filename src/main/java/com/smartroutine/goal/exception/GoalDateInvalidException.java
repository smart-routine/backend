package com.smartroutine.goal.exception;

import java.time.LocalDate;

public class GoalDateInvalidException extends IllegalArgumentException {

    public GoalDateInvalidException(LocalDate startDate, LocalDate endDate) {
        super("startDate" + startDate + " cannot be after endDate " + endDate );
    }
}
