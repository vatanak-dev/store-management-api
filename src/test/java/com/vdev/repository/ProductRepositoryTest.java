package com.vdev.repository;

import com.vdev.entity.Product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldSave_And_FindProduct(){
        Product product = new Product();

        product.setName("Laptop");
        product.setPrice(new java.math.BigDecimal("1000"));
        product.setQuantity(10);

        Product savedProduct = productRepository.save(product);
        Optional<Product> foundProduct = productRepository.
                findById(savedProduct.getId());

        assertTrue(foundProduct.isPresent());
    }
}
