package com.smartroutine.goal.mapper;

import com.smartroutine.goal.dto.GoalCreateRequest;
import com.smartroutine.goal.dto.GoalCreateResponse;
import com.smartroutine.goal.dto.GoalReadResponse;
import com.smartroutine.goal.entity.Goal;
import java.util.UUID;

public class GoalMapper {

    public static Goal toEntity(UUID userId, GoalCreateRequest request) {
        return Goal.builder()
            .userId(userId)
            .categoryId(request.getCategoryId())
            .goalName(request.getGoalName())
            .goalStatus(request.getGoalStatus())
            .priority(request.getPriority())
            .startDate(request.getStartDate())
            .endDate(request.getEndDate())
            .build();
    }

    public static GoalCreateResponse toCreateResponse(Goal goal) {
        return new GoalCreateResponse(goal.getGoalId(), goal.getUserId(), goal.getCategoryId(),
            goal.getGoalName(), goal.getGoalStatus(), goal.getPriority(),
            goal.getStartDate(), goal.getEndDate(), goal.getCreatedAt(), goal.getCreatedBy());
    }

    public static GoalReadResponse toReadResponse(Goal goal) {
        return new GoalReadResponse(goal.getGoalId(), goal.getUserId(), goal.getCategoryId(),
            goal.getGoalName(), goal.getGoalStatus(), goal.getPriority(), goal.getStartDate(),
            goal.getEndDate(), goal.getCreatedAt(), goal.getCreatedBy(), goal.getUpdatedAt(), goal.getUpdatedBy());
    }

}
