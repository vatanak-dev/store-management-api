package com.vdev.controller;

import com.vdev.entity.Product;
import com.vdev.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    public ProductController (ProductService productService){
        this.productService = productService;
    }


    @GetMapping("/products")
    public String getProducts(){
        return productService.getMessage();
    }

    @GetMapping
    public List<Product> getAllProduct(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product product (@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct (@PathVariable Long id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }
}

