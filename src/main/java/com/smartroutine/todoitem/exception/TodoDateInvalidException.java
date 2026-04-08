package com.smartroutine.todoitem.exception;

import java.time.LocalDateTime;

public class TodoDateInvalidException extends IllegalArgumentException {

    public TodoDateInvalidException(LocalDateTime startDate, LocalDateTime endDate) {
        super("startDate" + startDate + " cannot be after endDate " + endDate );
    }

    public TodoDateInvalidException(LocalDateTime startDate){
        super("startDate :" + startDate + "cannot be before now");
    }
}
