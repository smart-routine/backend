package com.smartroutine.todoitem.service;

import com.smartroutine.todoitem.dto.TodoCreateRequest;
import com.smartroutine.todoitem.dto.TodoCreateResponse;
import com.smartroutine.todoitem.entity.TodoItem;
import com.smartroutine.todoitem.exception.TodoDateInvalidException;
import com.smartroutine.todoitem.mapper.TodoMapper;
import com.smartroutine.todoitem.repository.TodoRepository;
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

}
