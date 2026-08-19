package com.vdev.controller;

import com.vdev.dto.ProductResponseDTO;
import com.vdev.exception.ProductNotFoundException;
import com.vdev.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Test
    void getProductById_shouldReturnProduct() throws Exception {
        ProductResponseDTO responseDTO = new ProductResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setName("Laptop");

        when(productService.getProductById(1L)).
                thenReturn(responseDTO);

        mockMvc.perform(get("/products/1")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.id").value(1)).
                andExpect(jsonPath("$.name").value("Laptop"));

        verify(productService).getProductById(1L);
    }
    @Test
    void getProductById_shouldReturnNotFound() throws Exception {

        when(productService.getProductById(999L)).
                thenThrow(new ProductNotFoundException(
                        "Product not found!"
                ));
        mockMvc.perform(get("/products/999")).
                andExpect(status().isNotFound()).
                andExpect(jsonPath("$.status").value(404)).
                andExpect(jsonPath("$.message").value("Product not found!"));

        verify(productService).getProductById(999L);
    }


}
