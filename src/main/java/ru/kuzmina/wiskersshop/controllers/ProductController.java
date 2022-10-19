package ru.kuzmina.wiskersshop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kuzmina.wiskersshop.exceptions.ResourceNotFoundException;
import ru.kuzmina.wiskersshop.model.Product;
import ru.kuzmina.wiskersshop.model.dtos.ProductDto;
import ru.kuzmina.wiskersshop.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> findAllProducts() {
        return productService.findAll().stream()
                .map(product -> new ProductDto(product.getId(), product.getTitle(), product.getDescription(), product.getPrice()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт не найден id:" + id));
        return new ProductDto(product.getId(), product.getTitle(), product.getDescription(), product.getPrice());
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }

}
