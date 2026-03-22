package com.smartroutine.goal.service;

import com.smartroutine.goal.dto.GoalCreateRequest;
import com.smartroutine.goal.dto.GoalCreateResponse;
import com.smartroutine.goal.dto.GoalDeleteResponse;
import com.smartroutine.goal.dto.GoalReadResponse;
import com.smartroutine.goal.dto.GoalUpdateRequest;
import com.smartroutine.goal.dto.GoalUpdateResponse;
import com.smartroutine.goal.entity.Goal;
import com.smartroutine.goal.exception.GoalAlreadyExistsException;
import com.smartroutine.goal.exception.GoalNotFoundException;
import com.smartroutine.goal.mapper.GoalMapper;
import com.smartroutine.goal.repository.GoalRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;

    private Goal getGoal(UUID goalId) {
        return goalRepository.findById(goalId)
            .orElseThrow(() -> new GoalNotFoundException(goalId));
    }

    @Transactional
    public GoalCreateResponse createGoal(UUID userId, GoalCreateRequest request) {
        Goal goal = GoalMapper.toEntity(userId, request);

        //(userId, categoryId, goalName) Unique
        if(goalRepository.existsByUserIdAndCategoryIdAndGoalName(userId, request.getCategoryId(), request.getGoalName())) {
            throw new GoalAlreadyExistsException(request.getGoalName());
        }

        Goal saveGoal = goalRepository.save(goal);

        return GoalMapper.toCreateResponse(saveGoal);
    }

    @Transactional(readOnly = true)
    public List<GoalReadResponse> readGoals(UUID userId) {
        List<Goal> goals = goalRepository.findAllByUserId(userId);

        return goals
            .stream()
            .map(GoalMapper::toReadResponse)
            .toList();
    }

    @Transactional(readOnly = true)
    public GoalReadResponse readGoal(UUID goalId) {
        Goal goal = getGoal(goalId);

        return GoalMapper.toReadResponse(goal);
    }

    @Transactional
    public GoalUpdateResponse updateGoal(UUID userId, UUID goalId, GoalUpdateRequest request) {
        Goal goal = getGoal(goalId);
        goal.validateOwner(userId);

        if(goalRepository.existsByUserIdAndCategoryIdAndGoalNameAndGoalIdNot(userId, goal.getCategoryId(),request.getGoalName(), goalId)) {
            throw new GoalAlreadyExistsException(request.getGoalName());
        }

        goal.update(request.getGoalName(), request.getGoalStatus(), request.getPriority(),
            request.getStartDate(), request.getEndDate());

        return GoalMapper.toUpdateResponse(goal);
    }

    @Transactional
    public GoalDeleteResponse deleteGoal(UUID goalId, UUID userId){
        Goal goal = getGoal(goalId);

        goal.validateOwner(userId);

        goal.delete(userId);

        return new GoalDeleteResponse(goalId, "success");
    }

}
