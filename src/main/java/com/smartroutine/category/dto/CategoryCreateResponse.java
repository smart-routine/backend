package com.smartroutine.category.dto;

import com.smartroutine.category.entity.CategoryColor;
import java.util.UUID;
import lombok.Getter;

@Getter
public class CategoryCreateResponse {

    private UUID id;
    private String categoryName;
    private CategoryColor color;

    public CategoryCreateResponse(UUID id, String categoryName, CategoryColor color) {
        this.id = id;
        this.categoryName = categoryName;
        this.color = color;
    }

}
