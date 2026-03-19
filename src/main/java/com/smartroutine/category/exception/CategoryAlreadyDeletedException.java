package com.smartroutine.category.exception;

import java.util.UUID;

public class CategoryAlreadyDeletedException extends RuntimeException {

    public CategoryAlreadyDeletedException(UUID id) {
        super("id: " + id + " is already deleted" );
    }
}
