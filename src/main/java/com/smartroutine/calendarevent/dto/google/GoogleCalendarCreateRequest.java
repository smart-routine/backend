package com.smartroutine.calendarevent.dto.google;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GoogleCalendarCreateRequest {

    private String title;
    private String description;
    private LocalDateTime startAt;
    private LocalDateTime endAt;


}
