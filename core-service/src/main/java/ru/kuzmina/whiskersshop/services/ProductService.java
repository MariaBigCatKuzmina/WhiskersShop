package ru.kuzmina.whiskersshop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kuzmina.whiskersshop.api.dtos.ProductDto;
import ru.kuzmina.whiskersshop.converters.ProductConverter;
import ru.kuzmina.whiskersshop.model.Product;
import ru.kuzmina.whiskersshop.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ProductConverter productConverter;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id) ;
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product createNewProduct(ProductDto productDto){
        Product product = new Product();
        product = productConverter.dtoToEntity(productDto);
        product.setId(null);
//        product.setTitle(productDto.getTitle());
//        product.setDescription(productDto.getDescription());
//        product.setPrice(productDto.getPrice());
//        product.setCategory(categoryService.findByTitle(productDto.getCategoryTitle()).);
        return productRepository.save(product);
    }

}
