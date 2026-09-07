package com.vdev.dto;

import java.util.List;

public class CategoryResponseDTO {
    private Long id;
    private String name;
    private List<ProductSummaryDTO> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductSummaryDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductSummaryDTO> products) {
        this.products = products;
    }
}
