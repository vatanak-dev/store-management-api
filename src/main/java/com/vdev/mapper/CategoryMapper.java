package com.vdev.mapper;

import com.vdev.dto.CategoryResponseDTO;
import com.vdev.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponseDTO toCategoryResponseDTO(Category category);
}
