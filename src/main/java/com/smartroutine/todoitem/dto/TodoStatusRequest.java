package com.smartroutine.todoitem.dto;

import com.smartroutine.todoitem.entity.TodoStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class TodoStatusRequest {
    @NotNull
    private TodoStatus status;
}
