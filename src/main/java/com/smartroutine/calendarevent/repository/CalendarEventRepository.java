package com.smartroutine.calendarevent.repository;

import com.smartroutine.calendarevent.entity.CalendarEvent;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarEventRepository extends JpaRepository<CalendarEvent, UUID> {

}
