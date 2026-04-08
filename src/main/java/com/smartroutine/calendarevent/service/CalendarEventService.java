package com.smartroutine.calendarevent.service;

import com.smartroutine.calendarevent.dto.CalendarEventCreateRequest;
import com.smartroutine.calendarevent.dto.CalendarEventCreateResponse;
import com.smartroutine.calendarevent.entity.CalendarEvent;
import com.smartroutine.calendarevent.mapper.CalendarEventMapper;
import com.smartroutine.calendarevent.repository.CalendarEventRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CalendarEventService {

    private final CalendarEventRepository calendarEventRepository;

    @Transactional
    public CalendarEventCreateResponse createCalendarEvent(UUID testUserId, CalendarEventCreateRequest request) {

        CalendarEvent calendarEvent = CalendarEventMapper.toEntity(testUserId, request);

        CalendarEvent saveCalendarEvent = calendarEventRepository.save(calendarEvent);

        return CalendarEventMapper.toCreateResponse(saveCalendarEvent);
    }
}
