package com.vdev.controller;

import com.vdev.entity.Category;
import com.vdev.service.CategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }
    @GetMapping("/{id}/products/count")
    public int getProductCount (@PathVariable Long id){
        return categoryService.getProductCount(id);
    }

}
