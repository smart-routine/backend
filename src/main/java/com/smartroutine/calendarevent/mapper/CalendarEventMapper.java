package com.smartroutine.calendarevent.mapper;

import com.smartroutine.calendarevent.dto.CalendarEventCreateRequest;
import com.smartroutine.calendarevent.dto.CalendarEventCreateResponse;
import com.smartroutine.calendarevent.dto.CalendarEventDeleteResponse;
import com.smartroutine.calendarevent.dto.CalendarEventReadResponse;
import com.smartroutine.calendarevent.dto.CalendarEventUpdateResponse;
import com.smartroutine.calendarevent.entity.CalendarEvent;
import java.util.UUID;

public class CalendarEventMapper {

    public static CalendarEvent toEntity(UUID userId, CalendarEventCreateRequest request, String googleEventId) {
        return  CalendarEvent.builder()
            .userId(userId)
            .eventSource(request.getEventSource())
            .title(request.getTitle())
            .description(request.getDescription())
            .googleEventId(googleEventId)
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

    public static CalendarEventReadResponse toReadResponse(CalendarEvent calendarEvent) {
        return new CalendarEventReadResponse(
            calendarEvent.getEventId(), calendarEvent.getUserId(),
            calendarEvent.getEventSource(), calendarEvent.getTodoId(),
            calendarEvent.getGoogleEventId(), calendarEvent.getTitle(),
            calendarEvent.getDescription(), calendarEvent.getColor(),
            calendarEvent.getStartAt(), calendarEvent.getEndAt(),
            calendarEvent.getCreatedAt(), calendarEvent.getCreatedBy(),
            calendarEvent.getUpdatedAt(), calendarEvent.getUpdatedBy()
        );
    }

    public static CalendarEventUpdateResponse toUpdateResponse(CalendarEvent calendarEvent) {
        return new CalendarEventUpdateResponse(
            calendarEvent.getEventId(), calendarEvent.getUserId(),
            calendarEvent.getEventSource(), calendarEvent.getTodoId(),
            calendarEvent.getGoogleEventId(), calendarEvent.getTitle(),
            calendarEvent.getDescription(), calendarEvent.getColor(),
            calendarEvent.getStartAt(), calendarEvent.getEndAt(),
            calendarEvent.getCreatedAt(), calendarEvent.getCreatedBy(),
            calendarEvent.getUpdatedAt(), calendarEvent.getUpdatedBy()
        );
    }

    public static CalendarEventDeleteResponse toDeleteResponse(CalendarEvent calendarEvent) {
        return new CalendarEventDeleteResponse(
            calendarEvent.getEventId(), calendarEvent.getDeletedAt(), calendarEvent.getDeletedBy(), "success"
        );
    }

}
