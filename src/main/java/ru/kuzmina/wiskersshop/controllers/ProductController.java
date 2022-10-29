package ru.kuzmina.wiskersshop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kuzmina.wiskersshop.model.Product;
import ru.kuzmina.wiskersshop.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController  {

    private final ProductService productService;

    @GetMapping
    public List<Product> findAllProducts() {
        return  productService.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable(name = "id") Long id) {
        return productService.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable(name = "id") Long id) {
        productService.deleteById(id);
    }


}
