package com.smartroutine.calendarevent.dto;

import com.smartroutine.calendarevent.entity.EventColor;
import com.smartroutine.calendarevent.entity.EventSource;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;

@Getter
public class CalendarEventCreateRequest {

    @NotNull
    private EventSource eventSource;

    private UUID todoId;

    @NotBlank
    @Size(min = 1, max = 100)
    private String title;

    @Size(max = 255)
    private String description;

    @NotNull
    private EventColor color;

    @NotNull
    private LocalDateTime startAt;

    @NotNull
    private LocalDateTime endAt;

}
