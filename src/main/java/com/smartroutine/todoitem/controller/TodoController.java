package com.smartroutine.todoitem.controller;

import com.smartroutine.todoitem.dto.TodoDeletedResponse;
import com.smartroutine.todoitem.dto.TodoReadResponse;
import com.smartroutine.todoitem.dto.TodoCreateRequest;
import com.smartroutine.todoitem.dto.TodoCreateResponse;
import com.smartroutine.todoitem.dto.TodoStatusRequest;
import com.smartroutine.todoitem.dto.TodoStatusResponse;
import com.smartroutine.todoitem.dto.TodoUpdateRequest;
import com.smartroutine.todoitem.dto.TodoUpdateResponse;
import com.smartroutine.todoitem.service.TodoService;
import com.smartroutine.user.security.CustomOAuth2User;
import jakarta.validation.Valid;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoCreateResponse> createTodo(
        @AuthenticationPrincipal CustomOAuth2User user,
        @Valid @RequestBody TodoCreateRequest request
    ){
        TodoCreateResponse response = todoService.createTodo(user.getUserId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/by-date")
    public ResponseEntity<List<TodoReadResponse>> getTodosByDate(
        @AuthenticationPrincipal CustomOAuth2User user,
        @RequestParam LocalDate date
    ){
        List<TodoReadResponse> responses = todoService.getTodosByDate(user.getUserId(), date);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/by-goal")
    public ResponseEntity<List<TodoReadResponse>> getTodosByGoalId(
        @AuthenticationPrincipal CustomOAuth2User user,
        @RequestParam UUID goalId
    ){
        List<TodoReadResponse> responses = todoService.getTodosByGoal(user.getUserId(), goalId);
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<TodoUpdateResponse> updateTodo(
        @AuthenticationPrincipal CustomOAuth2User user,
        @PathVariable UUID todoId,
        @Valid @RequestBody TodoUpdateRequest request
    ){
        TodoUpdateResponse response = todoService.updateTodo(todoId, user.getUserId(), request);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{todoId}/status")
    public ResponseEntity<TodoStatusResponse> updateStatus(
        @AuthenticationPrincipal CustomOAuth2User user,
        @PathVariable UUID todoId,
        @Valid @RequestBody TodoStatusRequest request
    ){
        TodoStatusResponse response = todoService.updateStatus(todoId, user.getUserId(), request.getStatus());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{todoId}")
    public ResponseEntity<TodoDeletedResponse> deleteTodo(
        @AuthenticationPrincipal CustomOAuth2User user,
        @PathVariable UUID todoId
    ){

        TodoDeletedResponse response = todoService.deleteTodo(todoId, user.getUserId());
        return  ResponseEntity.ok(response);
    }


}
