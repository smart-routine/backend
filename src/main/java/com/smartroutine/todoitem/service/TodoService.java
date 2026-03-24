package com.smartroutine.todoitem.service;

import com.smartroutine.todoitem.dto.TodoReadResponse;
import com.smartroutine.todoitem.dto.TodoCreateRequest;
import com.smartroutine.todoitem.dto.TodoCreateResponse;
import com.smartroutine.todoitem.dto.TodoStatusResponse;
import com.smartroutine.todoitem.dto.TodoUpdateRequest;
import com.smartroutine.todoitem.dto.TodoUpdateResponse;
import com.smartroutine.todoitem.entity.TodoItem;
import com.smartroutine.todoitem.entity.TodoStatus;
import com.smartroutine.todoitem.exception.TodoDateInvalidException;
import com.smartroutine.todoitem.exception.TodoNotFoundException;
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

    private TodoItem getTodo(UUID todoId, UUID userId){
        return todoRepository.findByTodoIdAndUserId(todoId, userId)
            .orElseThrow(() -> new TodoNotFoundException(todoId));
    }

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
        TodoItem todoItem = getTodo(todoId, userId);

        todoItem.updateTodo(userId, request.getTodoName(), request.getDuration(), request.getScheduledStartAt());

        return TodoMapper.todoUpdateResponse(todoItem);
    }

    @Transactional
    public TodoStatusResponse updateStatus(UUID todoId, UUID userId, TodoStatus status){
        TodoItem todoItem = getTodo(todoId, userId);

        todoItem.updateStatus(status);

        return TodoMapper.todoStatusResponse(todoItem);
    }
}
