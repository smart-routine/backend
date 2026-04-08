package com.smartroutine.todoitem.exception;

public class TodoNameInvalidException extends IllegalArgumentException{

    public TodoNameInvalidException(String todoName) {
        super(todoName + " is not a valid todoName");
    }
}
