package com.vdev.mapper;

import com.vdev.dto.ProductRequestDTO;
import com.vdev.dto.ProductResponseDTO;
import com.vdev.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponseDTO toResponse(Product product);

    void updateEntity(ProductRequestDTO requestDTO,
                      @MappingTarget Product product
    );
    List<ProductResponseDTO> toResponseList(List<Product> products);
}