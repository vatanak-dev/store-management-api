package com.vdev;

import com.vdev.dto.ProductResponseDTO;
import com.vdev.entity.Product;
import com.vdev.repository.ProductRepository;
import com.vdev.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class ProductIntegrationTest {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldGetProductById() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(new BigDecimal("99.99"));
        product.setQuantity(10);

        Product savedProduct = productRepository.save(product);

        ProductResponseDTO responseDTO = productService.getProductById(savedProduct.getId());
        assertNotNull(responseDTO);
        assertEquals(savedProduct.getId(), responseDTO.getId());
        assertEquals(savedProduct.getName(), responseDTO.getName());
        assertEquals(savedProduct.getPrice(), responseDTO.getPrice());
        assertEquals(savedProduct.getQuantity(), responseDTO.getQuantity());

    }

}
