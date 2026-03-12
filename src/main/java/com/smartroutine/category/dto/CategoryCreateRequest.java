package com.smartroutine.category.dto;

import com.smartroutine.category.entity.CategoryColor;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CategoryCreateRequest {

    @NotBlank
    private String categoryName;
    private CategoryColor color;

}
