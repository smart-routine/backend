package com.smartroutine.category.mapper;

import com.smartroutine.category.dto.CategoryCreateRequest;
import com.smartroutine.category.dto.CategoryCreateResponse;
import com.smartroutine.category.dto.CategoryReadResponse;
import com.smartroutine.category.dto.CategoryUpdateResponse;
import com.smartroutine.category.entity.Category;
import java.util.UUID;

public final class CategoryMapper {

    private CategoryMapper() {}

    public static Category toEntity(UUID userId, CategoryCreateRequest request) {
        return Category.of(userId, request.getCategoryName(), request.getColor());
    }

    public static CategoryCreateResponse toCreateResponse(Category category) {
        return new CategoryCreateResponse(
            category.getId(),
            category.getCategoryName(),
            category.getColor()
        );
    }

    public static CategoryReadResponse toResponse(Category category) {
        return new CategoryReadResponse(
            category.getId(),
            category.getUserId(),
            category.getCategoryName(),
            category.getColor()
        );
    }

    public static CategoryUpdateResponse toUpdateResponse(Category category) {
        return new CategoryUpdateResponse(
            category.getId(),
            category.getCategoryName(),
            category.getColor()
        );
    }

}
