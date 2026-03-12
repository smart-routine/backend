package com.smartroutine.category.mapper;

import com.smartroutine.category.dto.CategoryCreateRequest;
import com.smartroutine.category.dto.CategoryCreateResponse;
import com.smartroutine.category.entity.Category;
import com.smartroutine.category.entity.CategoryColor;
import java.util.UUID;

public class CategoryMapper {

    public static Category toEntity(UUID userId, CategoryCreateRequest request) {
        return Category.of(userId, request.getCategoryName(), request.getColor());
    }

    public static CategoryCreateResponse toResponse(Category category) {
        return new CategoryCreateResponse(
            category.getId(),
            category.getCategoryName(),
            category.getColor()
        );
    }



}
