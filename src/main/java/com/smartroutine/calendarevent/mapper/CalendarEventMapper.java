package com.smartroutine.calendarevent.mapper;

import com.smartroutine.calendarevent.dto.CalendarEventCreateRequest;
import com.smartroutine.calendarevent.dto.CalendarEventCreateResponse;
import com.smartroutine.calendarevent.entity.CalendarEvent;
import java.util.UUID;

public class CalendarEventMapper {

    public static CalendarEvent toEntity(UUID userId, CalendarEventCreateRequest request) {
        return  CalendarEvent.builder()
            .userId(userId)
            .eventSource(request.getEventSource())
            .title(request.getTitle())
            .description(request.getDescription())
            .googleEventId(request.getGoogleEventId())
            .todoId(request.getTodoId())
            .color(request.getColor())
            .startAt(request.getStartAt())
            .endAt(request.getEndAt())
            .build();
    }

    public static CalendarEventCreateResponse toCreateResponse(CalendarEvent calendarEvent) {
        return new CalendarEventCreateResponse(
            calendarEvent.getEventId(), calendarEvent.getUserId(),
            calendarEvent.getEventSource(), calendarEvent.getTodoId(),
            calendarEvent.getGoogleEventId(), calendarEvent.getTitle(),
            calendarEvent.getDescription(), calendarEvent.getColor(),
            calendarEvent.getStartAt(), calendarEvent.getEndAt(),
            calendarEvent.getCreatedAt(), calendarEvent.getCreatedBy());
    }

}
