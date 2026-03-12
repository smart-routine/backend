package com.smartroutine.category.controller;

import com.smartroutine.category.dto.CategoryCreateRequest;
import com.smartroutine.category.dto.CategoryCreateResponse;
import com.smartroutine.category.dto.CategoryReadResponse;
import com.smartroutine.category.service.CategoryService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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



}
