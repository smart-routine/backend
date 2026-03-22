package com.smartroutine.todoitem.mapper;

import com.smartroutine.todoitem.dto.TodoCreateRequest;
import com.smartroutine.todoitem.dto.TodoCreateResponse;
import com.smartroutine.todoitem.entity.TodoItem;
import com.smartroutine.todoitem.entity.TodoStatus;
import java.time.LocalDate;
import java.util.UUID;

public class TodoMapper {

    public static TodoItem toEntity(UUID userId, TodoCreateRequest request){
        return TodoItem.builder()
            .todoName(request.getTodoName())
            .userId(userId)
            .goalId(request.getGoalId())
            .duration(request.getDuration())
            .status(request.getStatus())
            .scheduledStartAt(request.getScheduledStartAt())
            .scheduledEndAt(request.getScheduledEndAt())
            .isAi(request.getIsAi())
            .build();
    }

    public static TodoCreateResponse toCreateResponse(TodoItem todoItem){
        return new TodoCreateResponse(todoItem.getTodoId(), todoItem.getTodoName(),
            todoItem.getUserId(), todoItem.getGoalId(), todoItem.getDuration(),
            todoItem.getStatus(), todoItem.getScheduledStartAt(), todoItem.getScheduledEndAt(),
            todoItem.getIsAi(), todoItem.getCreatedAt(), todoItem.getCreatedBy());
    }

}
