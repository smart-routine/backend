package com.smartroutine.category.exception;

public class CategoryNameInvalidException extends IllegalArgumentException {
  public CategoryNameInvalidException(String categoryName) {
    super(categoryName + " is invalid");
  }
}
