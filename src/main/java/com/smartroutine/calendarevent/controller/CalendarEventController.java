package com.smartroutine.calendarevent.controller;

import com.smartroutine.calendarevent.dto.CalendarEventCreateRequest;
import com.smartroutine.calendarevent.dto.CalendarEventCreateResponse;
import com.smartroutine.calendarevent.dto.CalendarEventDeleteResponse;
import com.smartroutine.calendarevent.dto.CalendarEventReadResponse;
import com.smartroutine.calendarevent.dto.CalendarEventUpdateRequest;
import com.smartroutine.calendarevent.dto.CalendarEventUpdateResponse;
import com.smartroutine.calendarevent.service.CalendarEventService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/period")
    public ResponseEntity<List<CalendarEventReadResponse>> readByPeriod(
        @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime startDate,
        @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime endDate){

        List<CalendarEventReadResponse> responses = calendarEventService.readCalendarEventsByPeriod(TEST_USER_ID, startDate, endDate);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/date")
    public ResponseEntity<List<CalendarEventReadResponse>> readByTargetDate(
        @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate targetDate){

        List<CalendarEventReadResponse> responses = calendarEventService.readCalendarEventsByTargetDate(TEST_USER_ID, targetDate);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<CalendarEventReadResponse> readById(@PathVariable UUID eventId){

        CalendarEventReadResponse response = calendarEventService.readCalendarEventDetail(TEST_USER_ID, eventId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{eventId}")
    public ResponseEntity<CalendarEventUpdateResponse> updateCalendarEvent(
        @PathVariable UUID eventId,
        @Valid @RequestBody CalendarEventUpdateRequest request
    ){
        CalendarEventUpdateResponse response = calendarEventService.updateCalendarEvent(TEST_USER_ID, eventId, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<CalendarEventDeleteResponse> deleteCalendarEvent(@PathVariable UUID eventId){

        CalendarEventDeleteResponse response = calendarEventService.deleteCalendarEvent(TEST_USER_ID, eventId);

        return ResponseEntity.ok(response);
    }

}
