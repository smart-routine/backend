package com.smartroutine.calendarevent.dto.google;

public record GoogleCalendarApiRequest(
    String summary,
    String description,
    GoogleCalendarDateTime start,
    GoogleCalendarDateTime end
) {

}
