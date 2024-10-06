package com.clothing.org.dto.request;


import com.clothing.org.entity.Category;
import com.clothing.org.entity.Gender;
import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private Integer price;
    private Integer size;
    private Gender gender;
    private Long categoryId;
}
