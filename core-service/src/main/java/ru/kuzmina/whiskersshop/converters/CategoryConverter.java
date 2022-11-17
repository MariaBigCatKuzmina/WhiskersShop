package ru.kuzmina.whiskersshop.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kuzmina.whiskersshop.api.dtos.CategoryDto;
import ru.kuzmina.whiskersshop.model.Category;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryConverter {
    private final ProductConverter productConverter;

    private CategoryDto entityToDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setTitle(category.getTitle());
        dto.setDescription(category.getDescription());
        dto.setProducts(category.getProductList().stream()
                .map(productConverter::entityToDto)
                .collect(Collectors.toList()));
        return dto;
    }

    private Category dtoToEntity(CategoryDto categoryDto){
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        category.setProductList(categoryDto.getProducts().stream()
                .map(productConverter::dtoToEntity)
                .collect(Collectors.toList()));
        return category;
    }
}
