package com.smartroutine.calendarevent.controller;

import com.smartroutine.calendarevent.dto.CalendarEventCreateRequest;
import com.smartroutine.calendarevent.dto.CalendarEventCreateResponse;
import com.smartroutine.calendarevent.service.CalendarEventService;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/calendar-events")
public class CalendarEventController {

    private static final UUID TEST_USER_ID = UUID.fromString("11111111-1111-1111-1111-111111111111");
    private final CalendarEventService calendarEventService;

    @PostMapping
    public ResponseEntity<CalendarEventCreateResponse> createCalendarEvent(
        @Valid @RequestBody CalendarEventCreateRequest request
    ){

        CalendarEventCreateResponse response = calendarEventService.createCalendarEvent(TEST_USER_ID, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
