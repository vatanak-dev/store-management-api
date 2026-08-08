package com.vdev.service;

import com.vdev.dto.ProductRequestDTO;
import com.vdev.dto.ProductResponseDTO;
import com.vdev.entity.Product;
import com.vdev.exception.ProductNotFoundException;
import com.vdev.mapper.ProductMapper;
import com.vdev.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(
            ProductRepository productRepository,
            ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductResponseDTO createProduct (ProductRequestDTO requestDTO) {
        Product product = new Product();
        product.setName(requestDTO.getName());
        product.setPrice(requestDTO.getPrice());
        product.setQuantity(requestDTO.getQuantity());
        Product savedProduct = productRepository.save(product);

        return productMapper.toResponse(savedProduct);
    }

    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.toResponseList(products);
    }

    public String getMessage(){
        return "Hello from ProductService!";
    }

    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found!"));
       return productMapper.toResponse(product);
    }
    public ProductResponseDTO updateProduct (Long id, ProductRequestDTO requestDTO){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found with ID: " + id));
        productMapper.updateEntity(requestDTO, product);

        Product updatedProduct = productRepository.save(product);
        return productMapper.toResponse(updatedProduct);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found with ID: " +id));
        productRepository.delete(product);
    }

    public List<ProductResponseDTO> searchByName(
            @RequestParam String name){
        List<Product> products = productRepository.findByNameContainingIgnoreCase(name);
        return products.stream()
                .map(productMapper::toResponse)
                .toList();
    }

    public List<ProductResponseDTO> findByMinPrice (@RequestParam BigDecimal minPrice){
        List<Product> products = productRepository.findByPriceLessThanEqual(minPrice);

        return products.stream()
                .map(productMapper::toResponse)
                .toList();
    }
    public List<ProductResponseDTO> findByMaxPrice (@RequestParam BigDecimal maxPrice){
        List<Product> products = productRepository.findByPriceGreaterThanEqual(maxPrice);

        return products.stream()
                .map(productMapper::toResponse)
                .toList();
    }
}
