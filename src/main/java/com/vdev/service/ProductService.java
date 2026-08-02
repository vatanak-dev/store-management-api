package com.vdev.service;

import com.vdev.entity.Product;
import com.vdev.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct (Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public String getMessage(){
        return "Hello from ProductService!";
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElse(null);
    }



}
