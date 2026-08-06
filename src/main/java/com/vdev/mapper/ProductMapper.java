package com.vdev.mapper;

import com.vdev.dto.ProductResponseDTO;
import com.vdev.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponseDTO toResponse(Product product);
}