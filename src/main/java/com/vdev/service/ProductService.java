package com.vdev.service;

import com.vdev.entity.Product;
import com.vdev.exception.ProductNotFoundException;
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
                .orElseThrow
                        (() -> new ProductNotFoundException("Product Not Found!"));
    }
    public Product updateProduct (Long id, Product product){
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));
        existingProduct.setName (product.getName());
        existingProduct.setPrice (product.getPrice());

        return productRepository.save(existingProduct);

    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found with ID: " +id));
        productRepository.delete(product);
    }
}
