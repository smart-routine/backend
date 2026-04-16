package com.smartroutine.calendarevent.exception;

import java.util.UUID;

public class CalendarEventAlreadyDeletedException extends RuntimeException {

    public CalendarEventAlreadyDeletedException(UUID eventId) {
        super(eventId.toString() + " is already deleted");
    }
}
