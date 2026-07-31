package com.vdev.controller;

import com.vdev.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ProductController {
    private final ProductService productService;
    public ProductController (ProductService productService){
        this.productService = productService;
    }


    @GetMapping("/products")
    public String getProducts(){
        return productService.getMessage();
    }
}

