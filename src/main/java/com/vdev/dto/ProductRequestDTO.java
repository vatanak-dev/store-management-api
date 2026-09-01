package com.vdev.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class ProductRequestDTO {
    @NotBlank(message = "Product Name is required")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100")
    private String name;

    @NotNull(message = "Product price is required")
    @Positive(message = "Product price must be a positive value.")
    private BigDecimal price;

    @NotNull(message = "Product quantity is required")
    @Positive(message = "Product quantity must be a positive value.")
    private Integer quantity;

    private Long categoryId;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
