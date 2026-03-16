package com.smartroutine.category.exception;

public class CategoryAlreadyExistsException extends RuntimeException {

    public CategoryAlreadyExistsException(String categoryName) {
        super("Category with name " + categoryName + " already exists");
    }
}
