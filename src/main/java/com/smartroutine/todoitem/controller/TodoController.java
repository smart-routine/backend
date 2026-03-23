package com.smartroutine.todoitem.controller;

import com.smartroutine.todoitem.dto.TodoReadResponse;
import com.smartroutine.todoitem.dto.TodoCreateRequest;
import com.smartroutine.todoitem.dto.TodoCreateResponse;
import com.smartroutine.todoitem.service.TodoService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;
    private static final UUID TEST_USER_ID = UUID.fromString("11111111-1111-1111-1111-111111111111");

    @PostMapping
    public ResponseEntity<TodoCreateResponse> createTodo(
        @Valid @RequestBody TodoCreateRequest request
    ){
        TodoCreateResponse response = todoService.createTodo(TEST_USER_ID, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TodoReadResponse>> getTodosByDate(
        @RequestParam LocalDate date
    ){
        List<TodoReadResponse> responses = todoService.getTodosByDate(TEST_USER_ID, date);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{goal_id}")
    public ResponseEntity<List<TodoReadResponse>> getTodosByGoalId(
        @PathVariable("goal_id") UUID goalId
    ){
        List<TodoReadResponse> responses = todoService.getTodosByGoal(TEST_USER_ID, goalId);
        return ResponseEntity.ok(responses);
    }

}
