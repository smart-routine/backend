package com.smartroutine.calendarevent.dto;

import com.smartroutine.calendarevent.entity.EventColor;
import com.smartroutine.calendarevent.entity.EventSource;
import java.time.LocalDateTime;
import java.util.UUID;

public record CalendarEventCreateResponse(
    UUID eventId,
    UUID userId,
    EventSource eventSource,
    UUID todoId,
    String googleEventId,
    String title,
    String description,
    EventColor color,
    LocalDateTime startAt,
    LocalDateTime endAt,
    LocalDateTime createdAt,
    UUID createdBy
) {
}
