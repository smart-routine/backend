package com.smartroutine.category.controller;

import com.smartroutine.category.dto.CategoryCreateRequest;
import com.smartroutine.category.dto.CategoryCreateResponse;
import com.smartroutine.category.dto.CategoryReadResponse;
import com.smartroutine.category.dto.CategoryUpdateRequest;
import com.smartroutine.category.dto.CategoryUpdateResponse;
import com.smartroutine.category.entity.CategoryColor;
import com.smartroutine.category.service.CategoryService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private static final UUID TEST_USER_ID = UUID.randomUUID();

    @PostMapping
    public ResponseEntity<CategoryCreateResponse> create(@RequestBody CategoryCreateRequest request) {
        CategoryCreateResponse response = categoryService.createCategory(TEST_USER_ID, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CategoryReadResponse>> readCategories() {
        List<CategoryReadResponse> responses = categoryService.readCategories(TEST_USER_ID);

        return ResponseEntity.ok(responses);
    }

    @GetMapping
    public ResponseEntity<CategoryReadResponse> readCategory(@PathVariable UUID id) {
        CategoryReadResponse response = categoryService.readCategory(id, TEST_USER_ID);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("{id}")
    public ResponseEntity<CategoryUpdateResponse> updateCategory(
        @PathVariable UUID id,
        @RequestBody CategoryUpdateRequest request){

        CategoryUpdateResponse response = categoryService.updateCategory(id, TEST_USER_ID,  request);

        return ResponseEntity.ok(response);
    }


}
