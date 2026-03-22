package com.smartroutine.goal.dto;

import com.smartroutine.goal.entity.GoalStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record GoalUpdateResponse(
    UUID goalId,
    UUID userId,
    UUID categoryId,
    String goalName,
    GoalStatus goalStatus,
    Integer priority,
    LocalDate startDate,
    LocalDate endDate,
    LocalDateTime createdAt,
    UUID createdBy,
    LocalDateTime updatedAt,
    UUID updatedBy
) {

}
