package com.vdev.controller;

import com.vdev.dto.ProductRequestDTO;
import com.vdev.dto.ProductResponseDTO;
import com.vdev.entity.Product;
import com.vdev.repository.ProductRepository;
import com.vdev.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductController (ProductService productService, ProductRepository productRepository){
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public String getProducts(){
        return productService.getMessage();
    }

    @GetMapping
    public List<ProductResponseDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponseDTO product (@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO createProduct(@Valid @RequestBody ProductRequestDTO requestDTO){
        return productService.createProduct(requestDTO);
    }

    @PutMapping("/{id}")
    public ProductResponseDTO updateProduct (@PathVariable Long id, @Valid @RequestBody ProductRequestDTO requestDTO){
        return productService.updateProduct(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  deleteProduct ( @PathVariable Long id){
        productService.deleteProduct(id);
    }

}

