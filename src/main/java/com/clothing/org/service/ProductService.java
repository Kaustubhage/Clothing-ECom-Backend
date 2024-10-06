package com.clothing.org.service;

import com.clothing.org.dto.request.ProductRequest;
import com.clothing.org.dto.response.ProductResponse;
import com.clothing.org.entity.Product;
import com.clothing.org.exception.NotFoundException;
import com.clothing.org.repository.CategoryRepository;
import com.clothing.org.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public String addProduct(ProductRequest productRequest) {
        Product product = getProduct(productRequest);
        productRepository.save(product);
        return "Product added successfully";
    }

    private Product getProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setSize(productRequest.getSize());
        product.setGender(productRequest.getGender());
        product.setCategory(
                categoryRepository.findById(productRequest.getCategoryId()).
                        orElseThrow(()
                                -> new NotFoundException("Category not found with given Id:" + productRequest.getCategoryId()))
        );
        return product;
    }


    public ProductResponse getProductById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found for given Id: " + productId));
        return getProductResponse(product);
    }

    private ProductResponse getProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setCategory(product.getCategory());
        productResponse.setSize(product.getSize());
        productResponse.setPrice(product.getPrice());
        productResponse.setGender(product.getGender());

        return productResponse;
    }

    public Page<Product> getProductsByCategory(Long categoryId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return productRepository.findByCategoryId(categoryId, pageable);
    }

    public Page<Product> getProducts(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        return productRepository.findAll(pageable);
    }

    public String updateProductById(Long productId, ProductRequest productRequest) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found for given Id: " + productId));
        product.setPrice(productRequest.getPrice());
        product.setGender(productRequest.getGender());
        product.setName(productRequest.getName());
        product.setSize(productRequest.getSize());
        product.setCategory(
                categoryRepository.findById(productRequest.getCategoryId()).
                        orElseThrow(()
                                -> new NotFoundException("Category not found with given Id:" + productRequest.getCategoryId()))
        );
        productRepository.save(product);
        return "Product details updated successfully";
    }

    public String deleteProductById(Long productId) {
        productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found for given Id: " + productId));
        productRepository.deleteById(productId);
        return "Product deleted successfully";
    }


}
