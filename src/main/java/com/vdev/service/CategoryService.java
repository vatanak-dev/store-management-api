package com.vdev.service;

import com.vdev.entity.Category;
import com.vdev.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService (CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }
    public int getProductCount (Long categoryId) {
        Category category = categoryRepository.findById(categoryId).
                orElseThrow();
        return category.getProducts().size();
    }


}
