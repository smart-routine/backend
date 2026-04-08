package com.smartroutine.todoitem.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record TodoDeletedResponse(
    UUID todoId,
    UUID userId,
    String message,
    LocalDateTime deletedAt,
    UUID deletedBy
) {

}
