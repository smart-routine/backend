package com.smartroutine.calendarevent.repository;

import com.smartroutine.calendarevent.entity.CalendarEvent;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarEventRepository extends JpaRepository<CalendarEvent, UUID> {

    List<CalendarEvent> findAllByUserIdAndStartAtLessThanEqualAndEndAtGreaterThanEqualOrderByStartAtAsc(
        UUID userId, LocalDateTime endDate, LocalDateTime startDate);

    List<CalendarEvent> findAllByUserIdAndStartAtLessThanAndEndAtGreaterThanOrderByStartAtAsc(UUID userId, LocalDateTime targetDateEnd, LocalDateTime targetDateStart);

    Optional<CalendarEvent> findByEventIdAndUserId(UUID calendarEventId, UUID userId);
}
