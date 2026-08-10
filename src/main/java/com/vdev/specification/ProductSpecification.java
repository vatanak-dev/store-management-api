package com.vdev.specification;

import com.vdev.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecification {
    public static Specification<Product> nameContains(String name){
        return (root, query, criteriaBuilder)
        -> criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                "%" + name.toLowerCase() + "%");
    }

    public static Specification<Product> priceGreaterThanOrEqualTo (BigDecimal minPrice){
        return (root, query, criteriaBuilder)
        -> criteriaBuilder.greaterThanOrEqualTo(
                root.get("price"),
                minPrice
        );
    }

    public static Specification<Product> priceLessThanOrEqualTo (BigDecimal maxPrice){
        return (root, query, criteriaBuilder)
        -> criteriaBuilder.lessThanOrEqualTo(
                root.get("price"),
                maxPrice
        );
    }
}
