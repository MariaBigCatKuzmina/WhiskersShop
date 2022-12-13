package ru.kuzmina.whiskersshop.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.kuzmina.whiskersshop.api.AppError;
import ru.kuzmina.whiskersshop.api.ResourceNotFoundException;
import ru.kuzmina.whiskersshop.api.dtos.ProductDto;
import ru.kuzmina.whiskersshop.converters.ProductConverter;
import ru.kuzmina.whiskersshop.model.Product;
import ru.kuzmina.whiskersshop.services.ProductService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Продукты", description = "методы работы с продуктами")
public class ProductController {

    private final ProductService productService;
    private final ProductConverter productConverter;


    @Operation(
            summary = "Получение отфильтрованных страниц с товарами",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Page.class))
                    )
            },
            parameters = {
                    @Parameter(name = "title", description = "Значение фильтра по названию товара", example = "корм"),
                    @Parameter(name = "minPrice", description = "Значение фильтра по минимальной цене товара", example = "1500"),
                    @Parameter(name = "maxPrice", description = "Значение фильтра по максимальной цене товара", example = "50"),
                    @Parameter(name = "page", description = "Номер выводимой страницы", example = "1")
            }
    )
    @GetMapping
    public Page<ProductDto> getProducts(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "min_price", required = false) BigDecimal minPrice,
            @RequestParam(name = "max_price", required = false) BigDecimal maxPrice,
            @RequestParam(name = "page", defaultValue = "1" ) Integer page) {

        if (page < 1) {
            page = 1;
        }
        return productService.findAll(title, minPrice, maxPrice, page - 1)
                .map(productConverter::entityToDto);
    }

    @Operation(
            summary = "Получение товара по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ),
                    @ApiResponse(
                            description = "Продукт не найден", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )

            }
    )
    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable @Parameter(description = "Идентификатор продукта", required = true) Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт не найден id:" + id));
        return productConverter.entityToDto(product);
    }

    @Operation(
            summary = "Удаление товара по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Продукт не найден", responseCode = "404"
                    )
            },
            parameters = {
                    @Parameter(name = "id", description = "Идентификатор товара", required = true)
            }
    )

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }

}
