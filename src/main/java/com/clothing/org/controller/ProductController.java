package com.clothing.org.controller;

import com.clothing.org.dto.request.ProductRequest;
import com.clothing.org.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping
    public ResponseEntity<Object> addProduct(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok().body(productService.addProduct(productRequest));
    }

    @GetMapping("/products/{product-id}")
    public ResponseEntity<Object> getProductById(@PathVariable("product-id") Long productId) {
        return ResponseEntity.ok().body(productService.getProductById(productId));
    }

    @GetMapping("/products")
    public ResponseEntity<Object> getProducts(@RequestParam(defaultValue = "0") int pageNo,
                                              @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok().body(productService.getProducts(pageNo, pageSize));
    }

    @GetMapping("categories/{category-id}/products")
    public ResponseEntity<Object> getProductsByCategory(@PathVariable("category-id") Long categoryId,
                                                        @RequestParam(defaultValue = "0") int pageNo,
                                                        @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok().body(productService.getProductsByCategory(categoryId, pageNo, pageSize));
    }

    @PutMapping("/products/{product-id}")
    public ResponseEntity<Object> updateProductById(@PathVariable("product-id") Long productId, @RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok().body(productService.updateProductById(productId, productRequest));
    }

    @DeleteMapping("/products/{product-id}")
    public ResponseEntity<Object> deleteProductById(@PathVariable("product-id") Long productId) {
        return ResponseEntity.ok().body(productService.deleteProductById(productId));
    }
}
