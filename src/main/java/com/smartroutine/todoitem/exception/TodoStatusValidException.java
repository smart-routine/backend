package com.smartroutine.todoitem.exception;

public class TodoStatusValidException extends IllegalArgumentException {

    public TodoStatusValidException() {
        super("status is invalid");
    }
}
