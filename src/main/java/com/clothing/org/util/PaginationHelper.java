package com.clothing.org.util;

import com.clothing.org.dto.response.CategoryResponse;
import com.clothing.org.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaginationHelper {

    public static Page<CategoryResponse> toResponsePage(Page<Category> productPage) {
        List<CategoryResponse> responseList = toResponseList(productPage.getContent());
        return new PageImpl<>(responseList, productPage.getPageable(), productPage.getTotalElements());
    }
    private static CategoryResponse toResponse(Category product) {
        if (product == null) {
            return null;
        }
        CategoryResponse response = new CategoryResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        return response;
    }

    private static List<CategoryResponse> toResponseList(List<Category> products) {
        return products.stream()
                .map(PaginationHelper::toResponse)
                .collect(Collectors.toList());
    }


}
