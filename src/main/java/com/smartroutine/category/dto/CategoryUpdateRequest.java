package com.smartroutine.category.dto;

import com.smartroutine.category.entity.CategoryColor;
import lombok.Getter;

@Getter
public class CategoryUpdateRequest {

    private String categoryName;
    private CategoryColor color;

}
