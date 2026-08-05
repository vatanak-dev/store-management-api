package com.vdev.service;

import com.vdev.entity.Product;
import com.vdev.exception.ProductNotFoundException;
import com.vdev.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import com.vdev.dto.ProductResponseDTO;
import com.vdev.dto.ProductRequestDTO;
import static org.apache.tomcat.util.net.openssl.OpenSSLStatus.setName;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDTO createProduct (ProductRequestDTO requestDTO) {
        Product product = new Product();
        product.setName(requestDTO.getName());
        product.setPrice(requestDTO.getPrice());
        product.setQuantity(requestDTO.getQuantity());
        Product savedProduct = productRepository.save(product);

        ProductResponseDTO responseDTO = new ProductResponseDTO();
        responseDTO.setId(savedProduct.getId());
        responseDTO.setName(savedProduct.getName());
        responseDTO.setPrice(savedProduct.getPrice());
        responseDTO.setQuantity(savedProduct.getQuantity());
        return responseDTO;
    }

    public List<ProductResponseDTO> getAllProducts() {

        return productRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public String getMessage(){
        return "Hello from ProductService!";
    }

    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow
                        (() -> new ProductNotFoundException("Product Not Found!"));
        ProductResponseDTO responseDTO = new ProductResponseDTO();
        responseDTO.setId(product.getId());
        responseDTO.setName(product.getName());
        responseDTO.setPrice(product.getPrice());
        responseDTO.setQuantity(product.getQuantity());
        return responseDTO;
    }
    public ProductResponseDTO updateProduct (Long id, ProductRequestDTO requestDTO){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));
        product.setName(requestDTO.getName());
        product.setPrice(requestDTO.getPrice());
        product.setQuantity(requestDTO.getQuantity());

        Product updatedProduct =  productRepository.save(product);

        ProductResponseDTO responseDTO = new ProductResponseDTO();
        responseDTO.setId(updatedProduct.getId());
        responseDTO.setName(updatedProduct.getName());
        responseDTO.setPrice(updatedProduct.getPrice());
        responseDTO.setQuantity(updatedProduct.getQuantity());

        return responseDTO;
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found with ID: " +id));
        productRepository.delete(product);
    }

    private ProductResponseDTO mapToResponse (Product product) {
        ProductResponseDTO responseDTO = new ProductResponseDTO();
        responseDTO.setId(product.getId());
        responseDTO.setName(product.getName());
        responseDTO.setPrice(product.getPrice());
        responseDTO.setQuantity(product.getQuantity());
        return responseDTO;
    }
}
