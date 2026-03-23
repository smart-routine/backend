package com.smartroutine.todoitem.dto;

import java.time.LocalDate;
import org.hibernate.validator.constraints.Length;

public class TodoUpdateRequest {
    @Length(max = 500)
    private String todoName;
    private Integer duration;
    private LocalDate scheduledStartAt;
}
