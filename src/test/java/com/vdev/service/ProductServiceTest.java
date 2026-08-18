package com.vdev.service;

import com.vdev.dto.ProductRequestDTO;
import com.vdev.exception.ProductNotFoundException;
import com.vdev.dto.ProductResponseDTO;
import com.vdev.entity.Product;
import com.vdev.mapper.ProductMapper;
import com.vdev.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @Test
    void getProductById_shouldReturnProduct(){

        Product product = new Product(
                1L,
                "Laptop",
                new BigDecimal("1000"),
                10);

        ProductResponseDTO responseDTO = new ProductResponseDTO();

        responseDTO.setId(1L);
        responseDTO.setName("Laptop");
        responseDTO.setPrice(new BigDecimal("1000"));
        responseDTO.setQuantity(10);

        when(productRepository.findById(1L)).
                thenReturn(Optional.of(product));
        when(productMapper.toResponse(product)).
                thenReturn(responseDTO);
        ProductResponseDTO result = productService.getProductById(1L);

        assertEquals(responseDTO.getId(), result.getId());
        assertEquals(responseDTO.getName(), result.getName());
        assertEquals(new BigDecimal("1000"), result.getPrice());
        assertEquals(responseDTO.getQuantity(), result.getQuantity());

        verify(productRepository).findById(1L);
    }

    @Test
    void getProductById_shouldThrowExceptionWhenProductNotFound(){
        when(productRepository.findById(999L)).
                thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () ->
                productService.getProductById(999L));
        verify(productRepository).findById(999L);

    }

    @Test
    void createProduct_ShouldReturnCreatedProduct(){
        ProductRequestDTO requestDTO = new ProductRequestDTO();

        Product savedProduct = new Product(
                1L,
                "Keyboard",
                new BigDecimal("50"),
                10);

        ProductResponseDTO responseDTO = new ProductResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setName("Keyboard");
        responseDTO.setPrice(new BigDecimal("50"));
        responseDTO.setQuantity(10);

        when(productRepository.save(any(Product.class))).
                thenReturn(savedProduct);
        when(productMapper.toResponse(savedProduct)).
                thenReturn(responseDTO);
        ProductResponseDTO result = productService.createProduct(requestDTO);
        assertEquals(responseDTO.getId(), result.getId());
        assertEquals(responseDTO.getName(), result.getName());
        assertEquals(responseDTO.getPrice(), result.getPrice());
        assertEquals(responseDTO.getQuantity(), result.getQuantity());

        verify(productRepository).save(any(Product.class));
        verify(productMapper).toResponse(savedProduct);

    }

}
