package com.smartroutine.category.controller;

import com.smartroutine.category.dto.CategoryCreateRequest;
import com.smartroutine.category.dto.CategoryCreateResponse;
import com.smartroutine.category.dto.CategoryDeleteResponse;
import com.smartroutine.category.dto.CategoryReadResponse;
import com.smartroutine.category.dto.CategoryUpdateRequest;
import com.smartroutine.category.dto.CategoryUpdateResponse;
import com.smartroutine.category.service.CategoryService;
import com.smartroutine.user.security.CustomOAuth2User;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<CategoryCreateResponse> create(
        @AuthenticationPrincipal CustomOAuth2User user,
        @Valid @RequestBody CategoryCreateRequest request
    ) {
        CategoryCreateResponse response = categoryService.createCategory(user.getUserId(), request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CategoryReadResponse>> readCategories(
        @AuthenticationPrincipal CustomOAuth2User user
    ) {
        List<CategoryReadResponse> responses = categoryService.readCategories(user.getUserId());

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryReadResponse> readCategory(
        @AuthenticationPrincipal CustomOAuth2User user,
        @PathVariable UUID id
    ) {
        CategoryReadResponse response = categoryService.readCategory(id, user.getUserId());

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryUpdateResponse> updateCategory(
        @AuthenticationPrincipal CustomOAuth2User user,
        @PathVariable UUID id,
        @Valid @RequestBody CategoryUpdateRequest request){

        CategoryUpdateResponse response = categoryService.updateCategory(id, user.getUserId(),  request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDeleteResponse> deleteCategory(
        @AuthenticationPrincipal CustomOAuth2User user,
        @PathVariable UUID id
    ) {
        CategoryDeleteResponse response = categoryService.deleteCategory(id, user.getUserId());

        return ResponseEntity.ok(response);
    }

}
