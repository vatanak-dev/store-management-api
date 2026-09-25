package com.vdev.dto;

import com.vdev.entity.enums.ProductStatus;
import jakarta.validation.constraints.NotNull;

public class ProductStatusRequestDTO {
    @NotNull(message = "Product status is required.")
    private ProductStatus status;

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }
}
