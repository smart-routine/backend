package com.smartroutine.calendarevent.mapper;

import com.smartroutine.calendarevent.dto.CalendarEventCreateRequest;
import com.smartroutine.calendarevent.dto.CalendarEventUpdateRequest;
import com.smartroutine.calendarevent.dto.google.GoogleCalendarCreateRequest;
import com.smartroutine.calendarevent.dto.google.GoogleCalendarUpdateRequest;
import com.smartroutine.calendarevent.entity.CalendarEvent;

public class GoogleCalendarMapper {

    public static GoogleCalendarCreateRequest toCreateRequest(CalendarEventCreateRequest request) {
        return new GoogleCalendarCreateRequest(request.getTitle(), request.getDescription(), request.getStartAt(), request.getEndAt());
    }

    public static GoogleCalendarUpdateRequest toUpdateRequest(CalendarEvent calendarEvent) {
        return new GoogleCalendarUpdateRequest(calendarEvent.getGoogleEventId(), calendarEvent.getTitle(), calendarEvent.getDescription(), calendarEvent.getStartAt(), calendarEvent.getEndAt());
    }

}
