package com.clothing.org.controller;

import com.clothing.org.dto.request.ProductRequest;
import com.clothing.org.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping
    public ResponseEntity<Object> addProduct(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok().body(productService.addProduct(productRequest));
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<Object> getProductById(@RequestParam("product-id") Long productId) {
        return ResponseEntity.ok().body(productService.getProductById(productId));
    }

    @GetMapping
    public ResponseEntity<Object> getProducts(@RequestParam(defaultValue = "0") int pageNo,
                                              @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok().body(productService.getProducts(pageNo, pageSize));
    }

    @GetMapping("/{category-id}")
    public ResponseEntity<Object> getProductsByCategory(@RequestParam("category-id") Long categoryId,
                                                        @RequestParam(defaultValue = "0") int pageNo,
                                                        @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok().body(productService.getProductsByCategory(categoryId, pageNo, pageSize));
    }

    @PutMapping("/{product-id}")
    public ResponseEntity<Object> updateProductById(@RequestParam("product-id") Long productId, @RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok().body(productService.updateProductById(productId, productRequest));
    }

    @DeleteMapping("/{product-id}")
    public ResponseEntity<Object> deleteProductById(@RequestParam("product-id") Long productId) {
        return ResponseEntity.ok().body(productService.deleteProductById(productId));
    }
}
