package com.clothing.org.controller;

import com.clothing.org.dto.request.CategoryRequest;
import com.clothing.org.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Object> addCategory(@RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.ok().body(categoryService.addCategory(categoryRequest));
    }

    @GetMapping("/{category-id}")
    public ResponseEntity<Object> getCategoryById(@PathVariable("category-id") Long categoryId) {
        return ResponseEntity.ok().body(categoryService.getCategoryById(categoryId));
    }

    @GetMapping
    public ResponseEntity<Object> getCategories(@RequestParam(defaultValue = "0") int pageNo,
                                                @RequestParam(defaultValue = "10") int pageSize ) {
        return ResponseEntity.ok().body(categoryService.getCategories(pageNo, pageSize));
    }

    @PutMapping("/{category-id}")
    public ResponseEntity<Object> updateCategoryById(@PathVariable("category-id") Long categoryId, @RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.ok().body(categoryService.updateCategoryById(categoryId, categoryRequest));
    }

    @DeleteMapping("/{category-id}")
    public ResponseEntity<Object> deleteCategoryById(@PathVariable("category-id") Long categoryId) {
        return ResponseEntity.ok().body(categoryService.deleteCategoryById(categoryId));
    }
}
