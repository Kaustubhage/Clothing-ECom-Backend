package com.clothing.org.service;

import com.clothing.org.dto.request.CategoryRequest;
import com.clothing.org.dto.response.CategoryResponse;
import com.clothing.org.entity.Category;
import com.clothing.org.exception.NotFoundException;
import com.clothing.org.repository.CategoryRepository;
import com.clothing.org.util.PaginationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    private PaginationHelper paginationHelper;

    public String addCategory(CategoryRequest categoryRequest) {
        // converted DTO to Entity
        Category category = new Category();
        category.setName(categoryRequest.getName());

        //save
        categoryRepository.save(category);
        return "Category added successfully";
    }


    public CategoryResponse getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Category not found with id: " + categoryId));
        return getCategoryResponse(category);
    }

    private static CategoryResponse getCategoryResponse(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setName(category.getName());
        response.setId(category.getId());
        return response;
    }


    public Page<CategoryResponse> getCategories(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Category> categories =  categoryRepository.findAll(pageable);
        return PaginationHelper.toResponsePage(categories);
    }

    public String updateCategoryById(Long categoryId, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Category not found with id: " + categoryId));
        category.setName(categoryRequest.getName());
        categoryRepository.save(category);

        return "Category Updated Successfully";
    }

    public String deleteCategoryById(Long categoryId) {
        categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Category not found with id: " + categoryId));
        categoryRepository.deleteById(categoryId);
        return "Category Deleted Successfully";
    }
}



