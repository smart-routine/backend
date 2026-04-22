package com.smartroutine.calendarevent.dto;

import com.smartroutine.calendarevent.entity.EventColor;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CalendarEventUpdateRequest {

    @Size(min = 1, max = 100)
    private String title;

    @Size(max = 255)
    private String description;

    private EventColor color;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

}
