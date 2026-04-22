package com.smartroutine.calendarevent.service;

import com.smartroutine.calendarevent.dto.CalendarEventCreateRequest;
import com.smartroutine.calendarevent.dto.CalendarEventCreateResponse;
import com.smartroutine.calendarevent.dto.CalendarEventDeleteResponse;
import com.smartroutine.calendarevent.dto.CalendarEventReadResponse;
import com.smartroutine.calendarevent.dto.CalendarEventUpdateRequest;
import com.smartroutine.calendarevent.dto.CalendarEventUpdateResponse;
import com.smartroutine.calendarevent.entity.CalendarEvent;
import com.smartroutine.calendarevent.exception.CalendarEventNotFoundException;
import com.smartroutine.calendarevent.exception.InvalidCalendarEventPeriodException;
import com.smartroutine.calendarevent.mapper.CalendarEventMapper;
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

    private CalendarEvent getCalendarEvent(UUID eventId, UUID userId) {
        return calendarEventRepository.findByEventIdAndUserId(eventId, userId)
            .orElseThrow(() -> new CalendarEventNotFoundException(eventId));
    }

    @Transactional
    public CalendarEventCreateResponse createCalendarEvent(UUID UserId, CalendarEventCreateRequest request) {

        CalendarEvent calendarEvent = CalendarEventMapper.toEntity(UserId, request);

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

        return CalendarEventMapper.toUpdateResponse(calendarEvent);
    }

    @Transactional
    public CalendarEventDeleteResponse deleteCalendarEvent(UUID userId, UUID eventId) {

        CalendarEvent calendarEvent = getCalendarEvent(eventId, userId);

        calendarEvent.delete();

        return CalendarEventMapper.toDeleteResponse(calendarEvent);
    }
}
