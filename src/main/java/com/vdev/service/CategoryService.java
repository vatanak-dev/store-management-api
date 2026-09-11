package com.vdev.service;

import com.vdev.dto.CategoryResponseDTO;
import com.vdev.entity.Category;
import com.vdev.mapper.CategoryMapper;
import com.vdev.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService (CategoryRepository categoryRepository, CategoryMapper categoryMapper){
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }
    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }
    public int getProductCount (Long categoryId) {
        Category category = categoryRepository.findById(categoryId).
                orElseThrow();
        return category.getProducts().size();
    }
    public CategoryResponseDTO getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).
                orElseThrow(() -> new RuntimeException(
                        "Category not found with ID: " + categoryId));
        return categoryMapper.toCategoryResponseDTO(category);
    }

}
