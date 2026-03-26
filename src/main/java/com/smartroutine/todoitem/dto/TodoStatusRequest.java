package com.smartroutine.todoitem.dto;

import com.smartroutine.todoitem.entity.TodoStatus;
import lombok.Getter;

@Getter
public class TodoStatusRequest {
    private TodoStatus status;
}
