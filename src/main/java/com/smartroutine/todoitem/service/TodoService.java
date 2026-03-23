package com.smartroutine.todoitem.service;

import com.smartroutine.todoitem.dto.TodoReadResponse;
import com.smartroutine.todoitem.dto.TodoCreateRequest;
import com.smartroutine.todoitem.dto.TodoCreateResponse;
import com.smartroutine.todoitem.dto.TodoUpdateRequest;
import com.smartroutine.todoitem.dto.TodoUpdateResponse;
import com.smartroutine.todoitem.entity.TodoItem;
import com.smartroutine.todoitem.exception.TodoDateInvalidException;
import com.smartroutine.todoitem.mapper.TodoMapper;
import com.smartroutine.todoitem.repository.TodoRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public TodoCreateResponse createTodo(UUID userId, TodoCreateRequest request){
        TodoItem todo = TodoMapper.toEntity(userId, request);

        if(request.getScheduledStartAt() != null && request.getScheduledEndAt() != null) {
            if(request.getScheduledStartAt().isAfter(request.getScheduledEndAt())) {
                throw new TodoDateInvalidException(request.getScheduledStartAt(), request.getScheduledEndAt());
            }
        }

        TodoItem saveTodo = todoRepository.save(todo);
        return TodoMapper.toCreateResponse(saveTodo);
    }

    @Transactional(readOnly = true)
    public List<TodoReadResponse> getTodosByDate(UUID userId, LocalDate date){
        List<TodoItem> todoItems = todoRepository.findAllByUserIdAndScheduledStartAt(userId, date);

        return todoItems
            .stream()
            .map(TodoMapper::toReadResponse)
            .toList();
    }

    @Transactional(readOnly = true)
    public List<TodoReadResponse> getTodosByGoal(UUID userId, UUID goalId){
        List<TodoItem> todoItems = todoRepository.findAllByUserIdAndGoalId(userId,goalId);

        return todoItems
            .stream()
            .map(TodoMapper::toReadResponse)
            .toList();
    }

    @Transactional
    public TodoUpdateResponse updateTodo(UUID todoId, UUID userId, TodoUpdateRequest request){
        TodoItem todoItem = todoRepository.findById(todoId)
            .orElseThrow(); // exception 처리
        // owner 검증
        // request 검증
        // 업데이트 메소드 : entity

        // ( 다른 메서드에서 투두 상태 처리 하기 )
        return null;
    }
}
