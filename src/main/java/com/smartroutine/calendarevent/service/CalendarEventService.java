package com.smartroutine.calendarevent.service;

import com.smartroutine.calendarevent.dto.CalendarEventCreateRequest;
import com.smartroutine.calendarevent.dto.CalendarEventCreateResponse;
import com.smartroutine.calendarevent.dto.CalendarEventDeleteResponse;
import com.smartroutine.calendarevent.dto.CalendarEventReadResponse;
import com.smartroutine.calendarevent.dto.CalendarEventUpdateRequest;
import com.smartroutine.calendarevent.dto.CalendarEventUpdateResponse;
import com.smartroutine.calendarevent.dto.google.GoogleCalendarCreateRequest;
import com.smartroutine.calendarevent.dto.google.GoogleCalendarCreateResponse;
import com.smartroutine.calendarevent.dto.google.GoogleCalendarUpdateRequest;
import com.smartroutine.calendarevent.entity.CalendarEvent;
import com.smartroutine.calendarevent.exception.CalendarEventNotFoundException;
import com.smartroutine.calendarevent.exception.InvalidCalendarEventPeriodException;
import com.smartroutine.calendarevent.mapper.CalendarEventMapper;
import com.smartroutine.calendarevent.mapper.GoogleCalendarMapper;
import com.smartroutine.calendarevent.repository.CalendarEventRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CalendarEventService {

    private final CalendarEventRepository calendarEventRepository;

    private final GoogleCalendarService googleCalendarService;

    private CalendarEvent getCalendarEvent(UUID eventId, UUID userId) {
        return calendarEventRepository.findByEventIdAndUserId(eventId, userId)
            .orElseThrow(() -> new CalendarEventNotFoundException(eventId));
    }

    @Transactional
    public CalendarEventCreateResponse createCalendarEvent(UUID userId, CalendarEventCreateRequest request) {

        GoogleCalendarCreateRequest googleCalendarCreateRequest = GoogleCalendarMapper.toCreateRequest(request);
        GoogleCalendarCreateResponse googleCalendarResponse = googleCalendarService.createGoogleCalendar(userId, googleCalendarCreateRequest);

        CalendarEvent calendarEvent = CalendarEventMapper.toEntity(userId, request, googleCalendarResponse.googleEventId());

        CalendarEvent saveCalendarEvent = calendarEventRepository.save(calendarEvent);

        return CalendarEventMapper.toCreateResponse(saveCalendarEvent);
    }

    public List<CalendarEventReadResponse> readCalendarEventsByPeriod(UUID userId, LocalDateTime startDate, LocalDateTime endDate) {
        if(startDate == null || endDate == null) {
            throw new InvalidCalendarEventPeriodException();
        }
        else if(startDate.isAfter(endDate)) {
            throw new InvalidCalendarEventPeriodException(startDate, endDate);
        }

        List<CalendarEvent> calendarEvents = calendarEventRepository.findAllByUserIdAndStartAtLessThanEqualAndEndAtGreaterThanEqualOrderByStartAtAsc(
            userId, endDate, startDate
        );

        return calendarEvents.stream().map(CalendarEventMapper::toReadResponse).toList();
    }

    public List<CalendarEventReadResponse> readCalendarEventsByTargetDate(UUID userId, LocalDate targetDate) {
        if(targetDate == null) {
            throw new InvalidCalendarEventPeriodException("targetDate is null");
        }

        LocalDateTime targetDateStart = targetDate.atStartOfDay();
        LocalDateTime targetDateEnd = targetDateStart.plusDays(1);

        List<CalendarEvent> calendarEvents = calendarEventRepository.findAllByUserIdAndStartAtLessThanAndEndAtGreaterThanOrderByStartAtAsc(userId, targetDateEnd, targetDateStart);

        return calendarEvents.stream().map(CalendarEventMapper::toReadResponse).toList();
    }

    public CalendarEventReadResponse readCalendarEventDetail(UUID userId, UUID eventId) {

        CalendarEvent calendarEvent = getCalendarEvent(eventId, userId);

        return CalendarEventMapper.toReadResponse(calendarEvent);
    }

    @Transactional
    public CalendarEventUpdateResponse updateCalendarEvent(UUID userId, UUID eventId, CalendarEventUpdateRequest request) {

        CalendarEvent calendarEvent = getCalendarEvent(eventId, userId);

        calendarEvent.update(userId, request.getTitle(), request.getDescription(), request.getColor(), request.getStartAt(), request.getEndAt());

        if(calendarEvent.getGoogleEventId() != null && !calendarEvent.getGoogleEventId().isBlank()) {
            GoogleCalendarUpdateRequest googleCalendarUpdateRequest = GoogleCalendarMapper.toUpdateRequest(calendarEvent);
            googleCalendarService.updateGoogleCalendar(userId, googleCalendarUpdateRequest);
        }

        return CalendarEventMapper.toUpdateResponse(calendarEvent);
    }

    @Transactional
    public CalendarEventDeleteResponse deleteCalendarEvent(UUID userId, UUID eventId) {

        CalendarEvent calendarEvent = getCalendarEvent(eventId, userId);

        if(calendarEvent.getGoogleEventId() != null && !calendarEvent.getGoogleEventId().isBlank()) {
            googleCalendarService.deleteGoogleCalendar(userId, calendarEvent.getGoogleEventId());
        }

        calendarEvent.delete();

        return CalendarEventMapper.toDeleteResponse(calendarEvent);
    }
}
