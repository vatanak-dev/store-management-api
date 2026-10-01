package com.vdev.repository;

import com.vdev.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>,
        JpaSpecificationExecutor<Product>
{
    Long id(Long id);

    @Query("SELECT p FROM Product p WHERE p.price > :minPrice")
    List<Product> findProductsWithPriceGreaterThanMinPrice(
            @Param("minPrice") BigDecimal minPrice);

    @Query("SELECT p FROM Product p WHERE p.price > :price AND p.quantity > :quantity")
    List<Product> findAbovePriceAndQuantity(
            @Param("price") BigDecimal price,
            @Param("quantity") Integer quantity);
}