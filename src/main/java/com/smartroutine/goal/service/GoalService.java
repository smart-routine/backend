package com.smartroutine.goal.service;

import com.smartroutine.category.exception.CategoryAlreadyExistsException;
import com.smartroutine.goal.dto.GoalCreateRequest;
import com.smartroutine.goal.dto.GoalCreateResponse;
import com.smartroutine.goal.entity.Goal;
import com.smartroutine.goal.mapper.GoalMapper;
import com.smartroutine.goal.repository.GoalRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;

    @Transactional
    public GoalCreateResponse createGoal(UUID userId, GoalCreateRequest request) {
        Goal goal = GoalMapper.toEntity(userId, request);

        //(userId, categoryId, goalName) Unique
        if(goalRepository.existsByUserIdAndCategoryIdAndGoalName(userId, request.getCategoryId(), request.getGoalName())) {
            throw new CategoryAlreadyExistsException(request.getGoalName());
        }

        Goal saveGoal = goalRepository.save(goal);

        return GoalMapper.toCreateResponse(userId, saveGoal);
    }

}
