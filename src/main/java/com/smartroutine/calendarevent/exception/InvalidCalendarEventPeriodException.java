package com.smartroutine.calendarevent.exception;

import java.time.LocalDateTime;

public class InvalidCalendarEventPeriodException extends IllegalArgumentException {

    public InvalidCalendarEventPeriodException(LocalDateTime startAt, LocalDateTime endAt) {
        super("startAt must be before endAt");
    }
}
