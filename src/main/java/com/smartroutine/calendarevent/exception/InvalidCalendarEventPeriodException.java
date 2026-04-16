package com.smartroutine.calendarevent.exception;

import java.time.LocalDateTime;

public class InvalidCalendarEventPeriodException extends IllegalArgumentException {

    public InvalidCalendarEventPeriodException() {
        super("startDate and endDate may not be null");
    }
    public InvalidCalendarEventPeriodException(LocalDateTime startAt, LocalDateTime endAt) {
        super("startDate :  " + startAt.toString() + " and endDate : " + endAt.toString() + " is invalid");
    }
    public InvalidCalendarEventPeriodException(String message) {
        super(message);
    }
}
