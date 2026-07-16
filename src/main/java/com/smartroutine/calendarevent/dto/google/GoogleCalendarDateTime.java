package com.smartroutine.calendarevent.dto.google;

import java.time.OffsetDateTime;

public record GoogleCalendarDateTime(
    OffsetDateTime dateTime,
    String TimeZone
) {

}
