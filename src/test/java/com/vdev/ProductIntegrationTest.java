package com.vdev;

import com.vdev.dto.ProductResponseDTO;
import com.vdev.entity.Product;
import com.vdev.repository.ProductRepository;
import com.vdev.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;


import java.math.BigDecimal;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest
@Transactional
public class ProductIntegrationTest {

    @Container
    static MySQLContainer<?> mysql =
            new MySQLContainer<>("mysql:8:0");
    @DynamicPropertySource
    static void configureProperties (DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }

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
