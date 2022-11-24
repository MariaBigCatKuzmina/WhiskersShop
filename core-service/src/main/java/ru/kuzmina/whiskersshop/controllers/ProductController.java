package ru.kuzmina.whiskersshop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kuzmina.whiskersshop.api.ResourceNotFoundException;
import ru.kuzmina.whiskersshop.api.dtos.ProductDto;
import ru.kuzmina.whiskersshop.converters.ProductConverter;
import ru.kuzmina.whiskersshop.model.Product;
import ru.kuzmina.whiskersshop.services.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
//@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;
    private final ProductConverter productConverter;

    @GetMapping
    public List<ProductDto> findAllProducts() {
        List<ProductDto> collect = productService.findAll().stream()
                .map(productConverter::entityToDto)
                .collect(Collectors.toList());
        return collect;
    }
    @PostMapping("/filter")
//    public List<ProductDto> getAllProductsAndFilter(@RequestBody(required = false) String title) {
    public List<ProductDto> getAllProductsAndFilter(@RequestParam(name = "titleFilter", required = false) String titleFilter,
                                                    @RequestParam(name = "minPriceFilter", required = false) BigDecimal minPriceFilter,
                                                    @RequestParam(name = "maxPriceFilter", required = false) BigDecimal maxPriceFilter) {
        List<ProductDto> collect = productService.findAllAndFilter(titleFilter, minPriceFilter, maxPriceFilter).stream()
                .map(productConverter::entityToDto)
                .collect(Collectors.toList());
        return collect;
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт не найден id:" + id));
        return productConverter.entityToDto(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }

}
