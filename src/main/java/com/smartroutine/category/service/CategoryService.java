package com.smartroutine.category.service;


import com.smartroutine.category.dto.CategoryCreateRequest;
import com.smartroutine.category.dto.CategoryCreateResponse;
import com.smartroutine.category.dto.CategoryReadResponse;
import com.smartroutine.category.dto.CategoryUpdateRequest;
import com.smartroutine.category.dto.CategoryUpdateResponse;
import com.smartroutine.category.entity.Category;
import com.smartroutine.category.mapper.CategoryMapper;
import com.smartroutine.category.repository.CategoryRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public CategoryCreateResponse createCategory(UUID userId, CategoryCreateRequest request) {

        Category category = CategoryMapper.toEntity(userId, request);

        // (userId, category) unique
        if(categoryRepository.existsByUserIdAndCategoryName(
            userId, category.getCategoryName())) {
            throw new RuntimeException();
        }

        Category savedCategory = categoryRepository.save(category);

        return CategoryMapper.toCreateResponse(savedCategory);
    }

    @Transactional(readOnly = true)
    public List<CategoryReadResponse> readCategories(UUID userId) {
        return categoryRepository.findAllByUserId(userId)
            .stream()
            .map(CategoryMapper::toResponse)
            .toList();
    }

    @Transactional(readOnly = true)
    public CategoryReadResponse readCategory(UUID id,  UUID userId) {
        Category category = categoryRepository.findByIdAndUserId(id, userId)
            .orElseThrow(() -> new RuntimeException("category not found"));
        return CategoryMapper.toResponse(category);
    }

    @Transactional
    public CategoryUpdateResponse updateCategory(UUID id, UUID userId,  CategoryUpdateRequest request) {
        Category category = categoryRepository.findByIdAndUserId(id, userId)
            .orElseThrow(() -> new RuntimeException("category not found"));

        category.update(request.getCategoryName(), request.getColor());

        return CategoryMapper.toUpdateResponse(category);
    }
}
