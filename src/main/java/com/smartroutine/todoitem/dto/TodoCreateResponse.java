package com.smartroutine.todoitem.dto;

import com.smartroutine.todoitem.entity.TodoStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public record TodoCreateResponse(
    UUID todoId,
    String todoName,
    UUID userId,
    UUID goalId,
    Integer duration,
    TodoStatus status,
    LocalDateTime scheduledStartAt,
    LocalDateTime scheduledEndAt,
    Boolean isAi,
    LocalDateTime createdAt,
    UUID createdBy
) {
}
