package com.smartroutine.category.service;


import com.smartroutine.category.dto.CategoryCreateRequest;
import com.smartroutine.category.dto.CategoryCreateResponse;
import com.smartroutine.category.entity.Category;
import com.smartroutine.category.mapper.CategoryMapper;
import com.smartroutine.category.repository.CategoryRepository;
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

        return CategoryMapper.toResponse(savedCategory);
    }


}
