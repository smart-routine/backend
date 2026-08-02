package com.smartroutine.calendarevent.exception;

public class GoogleCalendarUpdateFailed extends IllegalStateException {

    public GoogleCalendarUpdateFailed() {
        super("Google Calender event update failed");
    }
}
