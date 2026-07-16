package com.smartroutine.calendarevent.mapper;

import com.smartroutine.calendarevent.dto.CalendarEventCreateRequest;
import com.smartroutine.calendarevent.dto.google.GoogleCalendarCreateRequest;

public class GoogleCalendarMapper {

    public static GoogleCalendarCreateRequest toCreateRequest(CalendarEventCreateRequest request) {
        return new GoogleCalendarCreateRequest(request.getTitle(), request.getDescription(), request.getStartAt(), request.getEndAt());
    }

}
