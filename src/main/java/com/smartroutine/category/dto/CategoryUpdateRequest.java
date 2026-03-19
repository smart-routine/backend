package com.smartroutine.category.dto;

import com.smartroutine.category.entity.CategoryColor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CategoryUpdateRequest {

    @Size(min = 1, max = 50)
    private String categoryName;
    private CategoryColor color;

}
