package com.smartroutine.todoitem.dto;

import com.smartroutine.todoitem.entity.TodoStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record TodoCreateResponse(
    UUID todoId,
    String todoName,
    UUID userId,
    UUID goalId,
    Integer duration,
    TodoStatus status,
    LocalDate scheduledStartAt,
    LocalDate scheduledEndAt,
    Boolean isAi,
    LocalDateTime createAt,
    UUID createdBy
) {
}
