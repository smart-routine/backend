package com.smartroutine.calendarevent.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CalendarEventDeleteResponse(
    UUID eventId,
    LocalDateTime deletedAt,
    UUID deletedBy,
    String message
) {

}
