package com.smartroutine.calendarevent.exception;

public class GoogleCalendarDeleteFailed extends IllegalStateException {

    public GoogleCalendarDeleteFailed() {
        super("Google Calender event deleted failed");
    }
}
