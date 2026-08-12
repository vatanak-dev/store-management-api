package com.vdev.controller;

import com.vdev.dto.ProductRequestDTO;
import com.vdev.dto.ProductResponseDTO;

import com.vdev.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.math.BigDecimal;
import java.util.Locale;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController (ProductService productService){
        this.productService = productService;
    }

    @ApiResponse(
            responseCode = "200",
            description = "Product info loaded successfully"
    )
    @GetMapping
    public Page<ProductResponseDTO> getAllProducts(
            @Parameter(
                    description = "Search Product by Name",
                    example = "Laptop"
            )
            @RequestParam(required = false) String name,

            @Parameter(
                    description = "Minimum product price",
                    example = "500"
            )
            @RequestParam(required = false) BigDecimal minPrice,
            @Parameter(
                    description = "Maximum product price",
                    example = "2000"
            )
            @RequestParam(required = false) BigDecimal maxPrice,
            @PageableDefault(size = 10)
            Pageable pageable) {
        return productService.searchProducts(
                name,
                minPrice,
                maxPrice,
                pageable);
    }

    @Operation(
            summary = "Get product by ID",
            description = "Returns a product using its unique ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Product found Successfully"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Product not found"
    )
    @GetMapping("/{id}")
    public ProductResponseDTO product (
            @Parameter(
                    description = "The unique ID of Product",
                    example = "1"
    )
            @PathVariable Long id){
        return productService.getProductById(id);
    }
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Product information required to create a new product."
    )
    @Operation (
            summary = "Create Product",
            description = "Creates a new product using the provided product information"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Product created successfully",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            implementation = ProductResponseDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "The request contains invalid product data."
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO createProduct(
            @Valid @RequestBody ProductRequestDTO requestDTO){
        return productService.createProduct(requestDTO);
    }

    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Product information used to update the existing product."
    )
    @Operation(
            summary = "Update product",
            description = "Update existing product by id."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Product updated successfully."
    )
    @ApiResponse(
            responseCode = "404",
            description = "Product not found."
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid input."
    )
    @PutMapping("/{id}")
    public ProductResponseDTO updateProduct (
            @Parameter(
                    description = "The unique ID of the product to update",
                    example = "1"
            )
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDTO requestDTO){
        return productService.updateProduct(id, requestDTO);
    }

    @Operation(
            summary = "Delete the product",
            description = "Delete product with the existing ID."
    )
    @ApiResponse(
            responseCode = "204",
            description = "No Content."
    )
    @ApiResponse(
            responseCode = "404",
            description = "Product not found."
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  deleteProduct (
            @Parameter(
                    description = "Delete product with the specified ID.",
                    example = "1"
            )
            @PathVariable Long id){
        productService.deleteProduct(id);

    }
}