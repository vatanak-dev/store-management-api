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


}
