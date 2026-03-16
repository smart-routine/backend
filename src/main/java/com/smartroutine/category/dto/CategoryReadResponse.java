package com.smartroutine.category.dto;

import com.smartroutine.category.entity.CategoryColor;
import java.util.UUID;

public record CategoryReadResponse(UUID id, UUID userId, String categoryName, CategoryColor color) {

}
