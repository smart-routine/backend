package com.smartroutine.goal.controller;

import com.smartroutine.goal.dto.GoalCreateRequest;
import com.smartroutine.goal.dto.GoalCreateResponse;
import com.smartroutine.goal.service.GoalService;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/goals")
public class GoalController {

    private final GoalService goalService;
    private static final UUID TEST_USER_ID = UUID.fromString("11111111-1111-1111-1111-111111111111");

    @PostMapping
    public ResponseEntity<GoalCreateResponse> create(
        @Valid @RequestBody GoalCreateRequest request) {

        GoalCreateResponse response = goalService.createGoal(TEST_USER_ID, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
