package com.vdev.service;

import com.vdev.entity.Product;
import com.vdev.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.tomcat.util.net.openssl.OpenSSLStatus.setName;

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
    public Product updateProduct (Long id, Product product){
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));
        existingProduct.setName (product.getName());
        existingProduct.setPrice (product.getPrice());

        return productRepository.save(existingProduct);

    }
}
