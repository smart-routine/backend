package com.smartroutine.goal.controller;

import com.smartroutine.goal.dto.GoalCreateRequest;
import com.smartroutine.goal.dto.GoalCreateResponse;
import com.smartroutine.goal.dto.GoalDeleteResponse;
import com.smartroutine.goal.dto.GoalReadResponse;
import com.smartroutine.goal.dto.GoalUpdateRequest;
import com.smartroutine.goal.dto.GoalUpdateResponse;
import com.smartroutine.goal.service.GoalService;
import com.smartroutine.user.security.CustomOAuth2User;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/goals")
public class GoalController {

    private final GoalService goalService;

    @PostMapping
    public ResponseEntity<GoalCreateResponse> create(
        @AuthenticationPrincipal CustomOAuth2User user,
        @Valid @RequestBody GoalCreateRequest request
    ) {

        GoalCreateResponse response = goalService.createGoal(user.getUserId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<GoalReadResponse>> readGoals(
        @AuthenticationPrincipal CustomOAuth2User user
    ){
        List<GoalReadResponse> responses = goalService.readGoals(user.getUserId());

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{goal_id}")
    public ResponseEntity<GoalReadResponse> readGoal(
        @PathVariable UUID goal_id
    ){
        GoalReadResponse response = goalService.readGoal(goal_id);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{goal_id}")
    public ResponseEntity<GoalUpdateResponse> updateGoal(
        @AuthenticationPrincipal CustomOAuth2User user,
        @PathVariable UUID goal_id,
        @Valid @RequestBody GoalUpdateRequest request
    ){
        GoalUpdateResponse response = goalService.updateGoal(user.getUserId(), goal_id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{goal_id}")
    public ResponseEntity<GoalDeleteResponse> deleteGoal(
        @AuthenticationPrincipal CustomOAuth2User user,
        @PathVariable UUID goal_id
    ){
        GoalDeleteResponse response = goalService.deleteGoal(goal_id, user.getUserId());
        return ResponseEntity.ok(response);
    }


}
