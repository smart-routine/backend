package com.smartroutine.calendarevent.dto.google;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GoogleCalendarUpdateRequest {

    private String googleEventId;
    private String title;
    private String description;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
}
