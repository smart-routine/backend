package com.smartroutine.todoitem.controller;

import com.smartroutine.todoitem.dto.TodoCreateRequest;
import com.smartroutine.todoitem.dto.TodoCreateResponse;
import com.smartroutine.todoitem.service.TodoService;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/todo-items")
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

}
