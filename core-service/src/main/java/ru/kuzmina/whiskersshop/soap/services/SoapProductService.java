package ru.kuzmina.whiskersshop.soap.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kuzmina.whiskersshop.exceptions.ResourceNotFoundException;
import ru.kuzmina.whiskersshop.model.Product;
import ru.kuzmina.whiskersshop.repositories.ProductRepository;
import ru.kuzmina.whiskersshop.soap.soapProducts.SoapProduct;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SoapProductService {

    private final ProductRepository productRepository;

    public static final Function<Product, SoapProduct> functionProductToSoap = product -> {
        SoapProduct soapProduct = new SoapProduct();
        soapProduct.setId(product.getId());
        soapProduct.setTitle(product.getTitle());
        soapProduct.setDescription(product.getDescription());
        soapProduct.setPrice(BigDecimal.valueOf(product.getPrice()));
        return soapProduct;
    };

    public List<SoapProduct> getAllProducts(){
        return productRepository.findAll()
                .stream()
                .map(functionProductToSoap)
                .collect(Collectors.toList());
    }

    public SoapProduct getProductById(Long id) {
        return productRepository.findById(id)
                .map(functionProductToSoap)
                .orElseThrow(()->new ResourceNotFoundException("Продукт не найден id:" + id));
    }
}
