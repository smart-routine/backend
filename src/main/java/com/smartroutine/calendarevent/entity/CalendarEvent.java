package com.smartroutine.calendarevent.entity;

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
import lombok.NoArgsConstructor;

@Entity
@Table(name = "calendar_events")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Column(name = "google_event_id", length = 200)
    private String googleEventId;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 200)
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
            throw new IllegalArgumentException("startAt must be before endAt");
        }
    }

}
