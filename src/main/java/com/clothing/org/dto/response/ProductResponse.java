package com.clothing.org.dto.response;

import com.clothing.org.entity.Category;
import com.clothing.org.entity.Gender;
import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private Integer price;
    private Integer size;
    private Gender gender;
    private Category category;
}
