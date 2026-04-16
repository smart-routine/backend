package com.smartroutine.calendarevent.entity;

import com.smartroutine.calendarevent.exception.CalendarEventAlreadyDeletedException;
import com.smartroutine.calendarevent.exception.InvalidCalendarEventPeriodException;
import com.smartroutine.calendarevent.exception.InvalidCalendarEventSourceException;
import com.smartroutine.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "calendar_events")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SQLRestriction("deleted_at IS NULL")
public class CalendarEvent extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "event_id", unique = true, nullable = false)
    private UUID eventId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(nullable = false, name = "event_source")
    @Enumerated(EnumType.STRING)
    private EventSource eventSource;

    @Column(name = "todo_id")
    private UUID todoId;

    @Column(name = "google_event_id", length = 255)
    private String googleEventId;

    @Column(nullable = false, length = 100)
    private String title;

    @Column
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EventColor color;

    @Column(name = "start_at", nullable = false)
    private LocalDateTime startAt;

    @Column(name = "end_at", nullable = false)
    private LocalDateTime endAt;

    @Builder
    public CalendarEvent(UUID userId, EventSource eventSource, UUID todoId, String googleEventId, String title, String description, EventColor color, LocalDateTime startAt, LocalDateTime endAt){
        validatePeriod(startAt, endAt);
        validateEventSource(eventSource, todoId, googleEventId);

        this.userId = userId;
        this.eventSource = eventSource;
        this.todoId = todoId;
        this.googleEventId = googleEventId;
        this.title = title;
        this.description = description;
        this.color = color;
        this.startAt = startAt;
        this.endAt = endAt;

        super.create(userId);
    }

    private void validatePeriod(LocalDateTime startAt, LocalDateTime endAt) {
        if(!startAt.isBefore(endAt)) {
            throw new InvalidCalendarEventPeriodException(startAt, endAt);
        }
    }

    private void validateEventSource(EventSource eventSource, UUID todoId, String googleEventId) {
        if(eventSource == null) {
            throw new InvalidCalendarEventSourceException("eventSource must not be null");
        }
        else if(eventSource == EventSource.TODO){
            if(todoId == null) throw new InvalidCalendarEventSourceException("todoId must not be null");
            if(googleEventId != null && !googleEventId.isBlank()) throw new  InvalidCalendarEventSourceException(eventSource, ", googleEventId must be null");
        }
        else if(eventSource == EventSource.GOOGLE){
            if(googleEventId == null || googleEventId.isBlank()) throw new InvalidCalendarEventSourceException("googleEventId must not be null");
            if(todoId != null) throw new InvalidCalendarEventSourceException(eventSource, ", todoId must be null");
        }
        else {
            if(todoId != null || googleEventId != null) throw new InvalidCalendarEventSourceException(eventSource, ", todoId and googleEventId must be null");
        }
    }

    public void update(UUID userId, String title, String description, EventColor color, LocalDateTime startAt, LocalDateTime endAt) {

        if(title != null) this.title = title;
        if(description != null) this.description = description;
        if(color != null) this.color = color;

        LocalDateTime newStartAt = (startAt != null) ? startAt : this.startAt;
        LocalDateTime newEndAt = (endAt != null) ? endAt : this.endAt;

        validatePeriod(newStartAt, newEndAt);
        this.startAt = newStartAt;
        this.endAt = newEndAt;

        super.update(userId);
    }

    public void delete() {
        if(this.getDeletedAt() != null) {
            throw new CalendarEventAlreadyDeletedException(eventId);
        }

        super.delete(userId);
    }
}
