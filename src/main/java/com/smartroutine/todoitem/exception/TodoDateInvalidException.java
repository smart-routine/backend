package com.smartroutine.todoitem.exception;

import java.time.LocalDate;

public class TodoDateInvalidException extends IllegalArgumentException {

    public TodoDateInvalidException(LocalDate startDate, LocalDate endDate) {
        super("startDate" + startDate + " cannot be after endDate " + endDate );
    }
}
