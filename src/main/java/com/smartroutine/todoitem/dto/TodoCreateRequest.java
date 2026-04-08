package com.smartroutine.todoitem.dto;

import com.smartroutine.todoitem.entity.TodoStatus;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class TodoCreateRequest {

    @NotBlank
    @Length(max = 50)
    private String todoName;
    private UUID goalId;
    private Integer duration;
    private TodoStatus status;
    private LocalDateTime scheduledStartAt;
    private LocalDateTime scheduledEndAt;
    private Boolean isAi;

}
