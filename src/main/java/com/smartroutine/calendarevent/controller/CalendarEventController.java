package com.smartroutine.calendarevent.controller;

import com.smartroutine.calendarevent.dto.CalendarEventCreateRequest;
import com.smartroutine.calendarevent.dto.CalendarEventCreateResponse;
import com.smartroutine.calendarevent.dto.CalendarEventDeleteResponse;
import com.smartroutine.calendarevent.dto.CalendarEventReadResponse;
import com.smartroutine.calendarevent.dto.CalendarEventUpdateRequest;
import com.smartroutine.calendarevent.dto.CalendarEventUpdateResponse;
import com.smartroutine.calendarevent.service.CalendarEventService;
import com.smartroutine.user.security.CustomOAuth2User;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    private final CalendarEventService calendarEventService;

    @PostMapping
    public ResponseEntity<CalendarEventCreateResponse> createCalendarEvent(
        @AuthenticationPrincipal CustomOAuth2User user,
        @Valid @RequestBody CalendarEventCreateRequest request
    ){

        CalendarEventCreateResponse response = calendarEventService.createCalendarEvent(user.getUserId(), request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/period")
    public ResponseEntity<List<CalendarEventReadResponse>> readByPeriod(
        @AuthenticationPrincipal CustomOAuth2User user,
        @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime startDate,
        @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime endDate){

        List<CalendarEventReadResponse> responses = calendarEventService.readCalendarEventsByPeriod(user.getUserId(), startDate, endDate);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/date")
    public ResponseEntity<List<CalendarEventReadResponse>> readByTargetDate(
        @AuthenticationPrincipal CustomOAuth2User user,
        @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate targetDate){

        List<CalendarEventReadResponse> responses = calendarEventService.readCalendarEventsByTargetDate(user.getUserId(), targetDate);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<CalendarEventReadResponse> readById(
        @AuthenticationPrincipal CustomOAuth2User user,
        @PathVariable UUID eventId
    ){

        CalendarEventReadResponse response = calendarEventService.readCalendarEventDetail(user.getUserId(), eventId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{eventId}")
    public ResponseEntity<CalendarEventUpdateResponse> updateCalendarEvent(
        @AuthenticationPrincipal CustomOAuth2User user,
        @PathVariable UUID eventId,
        @Valid @RequestBody CalendarEventUpdateRequest request
    ){
        CalendarEventUpdateResponse response = calendarEventService.updateCalendarEvent(user.getUserId(), eventId, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<CalendarEventDeleteResponse> deleteCalendarEvent(
        @AuthenticationPrincipal CustomOAuth2User user,
        @PathVariable UUID eventId
    ){

        CalendarEventDeleteResponse response = calendarEventService.deleteCalendarEvent(user.getUserId(), eventId);

        return ResponseEntity.ok(response);
    }

}
