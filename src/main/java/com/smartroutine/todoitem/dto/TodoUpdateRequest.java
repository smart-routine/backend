package com.smartroutine.todoitem.dto;

import java.time.LocalDate;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class TodoUpdateRequest {
    @Length(max = 500)
    private String todoName;
    private Integer duration;
    private LocalDate scheduledStartAt;
}
