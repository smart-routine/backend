package com.smartroutine.category.controller;

import com.smartroutine.category.dto.CategoryCreateRequest;
import com.smartroutine.category.dto.CategoryCreateResponse;
import com.smartroutine.category.service.CategoryService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryCreateResponse> create(@RequestBody CategoryCreateRequest request) {
        // 임시 userId
        UUID userId = UUID.randomUUID();
        CategoryCreateResponse response = categoryService.createCategory(userId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



}
