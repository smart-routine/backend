package com.smartroutine.todoitem.exception;

import java.util.UUID;

public class TodoNotFoundException extends RuntimeException {

    public TodoNotFoundException(UUID todoId) {
      super("Todo Item with id " + todoId + " not found");
    }
}
