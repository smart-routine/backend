package com.smartroutine.category.dto;

import com.smartroutine.category.entity.CategoryColor;
import java.util.UUID;

public record CategoryCreateResponse(UUID id, String categoryName, CategoryColor color) {

}
