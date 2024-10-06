package com.clothing.org.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer price;
    private Integer size;
    private Gender gender;
    @JoinColumn(name = "categoryId")
    private Category category;
}
