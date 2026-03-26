package com.smartroutine.todoitem.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class TodoUpdateRequest {
    @Length(max = 50)
    private String todoName;
    private Integer duration;
    private LocalDateTime scheduledStartAt;
    private LocalDateTime scheduledEndAt;
}
