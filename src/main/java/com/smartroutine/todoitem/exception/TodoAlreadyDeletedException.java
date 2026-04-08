package com.smartroutine.todoitem.exception;

import java.util.UUID;

public class TodoAlreadyDeletedException extends RuntimeException {

    public TodoAlreadyDeletedException(UUID todoId) {
        super(todoId + "is already deleted");
    }
}
