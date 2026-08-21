package com.vdev.repository;

import com.vdev.entity.Product;

import com.vdev.specification.ProductSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void cleanDatabase(){
        productRepository.deleteAll();
    }

    @Test
    void shouldSave_And_FindProduct() {
        Product product = new Product();

        product.setName("Laptop");
        product.setPrice(new java.math.BigDecimal("1000"));
        product.setQuantity(10);

        Product savedProduct = productRepository.save(product);
        Optional<Product> foundProduct = productRepository.
                findById(savedProduct.getId());

        assertTrue(foundProduct.isPresent());
    }

    @Test
    void shouldFindProductByNameSpecification() {
        Product laptop = new Product();
        laptop.setName("Laptop");
        laptop.setPrice(new BigDecimal("1000"));
        laptop.setQuantity(10);

        Product laptopStand = new Product();
        laptopStand.setName("Laptop Stand");
        laptopStand.setPrice(new BigDecimal("50"));
        laptopStand.setQuantity(5);

        Product mouse = new Product();
        mouse.setName("Mouse");
        mouse.setPrice(new BigDecimal("25"));
        mouse.setQuantity(20);

        productRepository.saveAll(List.of(laptop, laptopStand, mouse));

        Specification<Product> specification = ProductSpecification.nameContains("Laptop");
        List<Product> foundProducts = productRepository.findAll(specification);

        assertEquals(2, foundProducts.size());
        assertTrue(foundProducts.stream().anyMatch(p -> p.getName().contains("Laptop")));
        assertTrue(foundProducts.stream().anyMatch(p -> p.getName().equals("Laptop Stand")));
    }

    @Test
    void shouldFindProductByMinimumPrice(){
        Product laptop =new Product();
        laptop.setName("Laptop");
        laptop.setPrice(new BigDecimal("1000"));
        laptop.setQuantity(10);

        Product laptopStand = new Product();
        laptopStand.setName("Laptop Stand");
        laptopStand.setPrice(new BigDecimal("50"));
        laptopStand.setQuantity(5);

        Product mouse = new Product();
        mouse.setName("Mouse");
        mouse.setPrice(new BigDecimal("25"));
        mouse.setQuantity(20);

        productRepository.saveAll(List.of(laptop, laptopStand, mouse));
        Specification<Product> specification = ProductSpecification.priceGreaterThanOrEqualTo(new BigDecimal("50"));
        List<Product> foundProducts = productRepository.findAll(specification);

        assertEquals(2, foundProducts.size());
        assertTrue(foundProducts.stream().
                anyMatch(p -> p.getName().equals("Laptop")));
        assertTrue(foundProducts.stream().
                anyMatch(p -> p.getName().equals("Laptop Stand")));

    }
    @Test
    void shouldFindProductByMaximumPrice(){
        Product laptop = new Product();
        laptop.setName("Laptop");
        laptop.setPrice(new BigDecimal("1000"));
        laptop.setQuantity(10);

        Product laptopStand = new Product();
        laptopStand.setName("Laptop Stand");
        laptopStand.setPrice(new BigDecimal("50"));
        laptopStand.setQuantity(5);

        Product mouse = new Product();
        mouse.setName("Mouse");
        mouse.setPrice(new BigDecimal("25"));
        mouse.setQuantity(20);

        productRepository.saveAll(List.of(laptop, laptopStand, mouse));
        Specification<Product> specification = ProductSpecification.priceLessThanOrEqualTo(new BigDecimal("50"));
        List<Product> foundProduct = productRepository.findAll(specification);

        assertEquals(2, foundProduct.size());
        assertTrue(foundProduct.stream().
                anyMatch(p -> p.getName().equals("Laptop Stand")));
        assertTrue(foundProduct.stream().
                anyMatch(p -> p.getName().equals("Mouse")));
    }
}