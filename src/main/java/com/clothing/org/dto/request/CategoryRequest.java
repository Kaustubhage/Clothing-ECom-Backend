package com.clothing.org.dto.request;

import com.clothing.org.entity.Product;
import lombok.*;

import java.util.List;

@Data
public class CategoryRequest {
    private String name;
    private List<Product> products;
    private Integer fromPrice;
    private Integer toPrice;
}
