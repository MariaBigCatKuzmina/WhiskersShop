package ru.kuzmina.whiskersshop.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kuzmina.whiskersshop.api.ResourceNotFoundException;
import ru.kuzmina.whiskersshop.api.dtos.ProductDto;
import ru.kuzmina.whiskersshop.model.Category;
import ru.kuzmina.whiskersshop.model.Product;
import ru.kuzmina.whiskersshop.services.CategoryService;

@Component
@RequiredArgsConstructor
public class ProductConverter {
    private final CategoryService categoryService;
    public ProductDto entityToDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setCategoryTitle(product.getCategory().getTitle());
        return productDto;
    }

    public Product dtoToEntity(ProductDto productDto){
        Product product =  new Product();
        product.setId(productDto.getId());
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Категория " + productDto.getCategoryTitle() + " не найдена"));
        product.setCategory(category);
        return product;
    }
}
