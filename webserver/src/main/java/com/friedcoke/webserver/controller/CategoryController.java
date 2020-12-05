package com.friedcoke.webserver.controller;

import com.friedcoke.webserver.model.Category;
import com.friedcoke.webserver.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public int addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @PutMapping(path = "{id}")
    public int updateCategory(@PathVariable("id") UUID categoryId, @RequestBody Category category) {
        return categoryService.updateCategory(categoryId, category);
    }

    @DeleteMapping(path = "{id}")
    public int deleteCategory(@PathVariable("id") UUID categoryId) {
        return categoryService.deleteCategory(categoryId);
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

}
