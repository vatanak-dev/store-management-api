package com.vdev.service;

import com.vdev.dto.ProductRequestDTO;
import com.vdev.dto.ProductResponseDTO;
import com.vdev.entity.Product;
import com.vdev.exception.ProductNotFoundException;
import com.vdev.mapper.ProductMapper;
import com.vdev.repository.ProductRepository;
import com.vdev.specification.ProductSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
    public Page<ProductResponseDTO> searchProducts (
            String name,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Pageable pageable){
        Specification<Product> specification = Specification.unrestricted();

        if (minPrice != null && minPrice.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException(
                    "minPrice cannot be negative"
            );
        }

        if (maxPrice != null && maxPrice.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException(
                    "maxPrice cannot be negative"
            );
        }

        if (minPrice != null && maxPrice != null && minPrice.compareTo(maxPrice) > 0) {
            throw new IllegalArgumentException(
                    "minPrice cannot be greater than maxPrice"
            );
        }

        if (name != null && !name.isBlank()) {
            specification = specification.and(
                    ProductSpecification.nameContains(name)
            );
        }
        if (minPrice != null) {
            specification = specification.and(
                    ProductSpecification.priceGreaterThanOrEqualTo(minPrice)
            );
        }
        if (maxPrice != null) {
            specification = specification.and(
                    ProductSpecification.priceLessThanOrEqualTo(maxPrice)
            );
        }
        Page<Product> products = productRepository.findAll(specification, pageable);

        return products.map(productMapper::toResponse);
    }

}
