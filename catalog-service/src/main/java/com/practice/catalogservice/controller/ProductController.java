package com.practice.catalogservice.controller;


import com.practice.catalogservice.dtos.PagedResult;
import com.practice.catalogservice.model.Product;
import com.practice.catalogservice.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public PagedResult<Product> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
        return productService.getProducts(pageNo);
    }

    @GetMapping("/{code}")
    public Product getByCode(@PathVariable String code) {
        return productService.getProductByCode(code);
    }
}