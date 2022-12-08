package ru.kuzmina.whiskersshop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.kuzmina.whiskersshop.api.dtos.ProductDto;
import ru.kuzmina.whiskersshop.converters.ProductConverter;
import ru.kuzmina.whiskersshop.model.Product;
import ru.kuzmina.whiskersshop.repositories.ProductRepository;
import ru.kuzmina.whiskersshop.repositories.specifications.ProductSpecifications;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public Page<Product> findAll(String title, BigDecimal minPrice, BigDecimal maxPrice, int page){
        Specification<Product> specByFilter = createSpecByFilter(minPrice, maxPrice, title);
        return productRepository.findAll(specByFilter, PageRequest.of(page, 5));
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id) ;
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product createNewProduct(ProductDto productDto){
        Product product = productConverter.dtoToEntity(productDto);
        product.setId(null);
        return productRepository.save(product);
    }

    public Specification<Product> createSpecByFilter(BigDecimal minPrice, BigDecimal maxPrice, String title) {
         Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpecifications.priceLessOrEqualsThan(maxPrice));
        }
        if (title != null) {
            spec = spec.and(ProductSpecifications.titleLike(title));
        }
        return spec;
    }

}
