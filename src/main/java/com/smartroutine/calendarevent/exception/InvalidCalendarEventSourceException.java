package com.smartroutine.calendarevent.exception;

import com.smartroutine.calendarevent.entity.EventSource;

public class InvalidCalendarEventSourceException extends IllegalArgumentException {

    public InvalidCalendarEventSourceException(String message) {
        super(message);
    }

    public InvalidCalendarEventSourceException(EventSource eventSource, String message) {
        super("event source: " + eventSource + message);
    }
}
